package com.sarc.class_service.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.stream.Collectors;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.sarc.class_service.*;

public class ClassService {
    private final ClassRepository ClassRepository;
    private final LessonRepository LessonRepository;

    @Autowired
    public ClassService(ClassRepository ClassRepository, LessonRepository LessonRepository) {
        this.ClassRepository = ClassRepository;
        this.LessonRepository = LessonRepository;
    }

    public String criaClass(ClassDTO ClassDTO) {
        String codigo = ClassDTO.getCodigo();

        if (ClassRepository.existsByCodigo(codigo)) {
            throw new IllegalArgumentException("Já existe uma Class com o código: " + codigo);
        }

        Lesson Lesson = LessonRepository.findLessonByCodigo(ClassDTO.getLessonCodigo());

        if (Lesson == null) {
            throw new RuntimeException("Lesson não encontrada");
        }

        Class Class = new Class(
                ClassDTO.getCodigo(),
                ClassDTO.getHorario(),
                Lesson,
                ClassDTO.getIdProfessor());

        return ClassRepository.save(Class).getCodigo();
    }

    public void atualizaCalendario(String codigoClass, String horario) throws Exception {
        Optional<Class> Class = ClassRepository.findById(codigoClass);

        if (Class.isEmpty())
            throw new Exception("A Class não existe");

        Class.get().setHorario(horario);
        ClassRepository.save(Class.get());
    }

    public void addAlunos(MultipartFile arquivo) throws Exception {
        String extensaoArquivo = arquivo.getContentType();
        List<RegistrationDTO> matriculas;
        assert extensaoArquivo != null;
        if (extensaoArquivo.equals("text/csv"))
            matriculas = lerCSV(arquivo);
        else
            matriculas = lerXLSX(arquivo);

        Map<String, List<String>> ClassEstudante = matriculas.stream()
                .collect(Collectors.groupingBy(
                        RegistrationDTO::codClass,
                        Collectors.mapping(RegistrationDTO::codEstudante, Collectors.toList())));

        List<String> codClasss = new ArrayList<>(ClassEstudante.keySet());
        List<Class> Classs = ClassRepository.findByCodigoIn(codClasss);

        Map<String, Class> ClassMap = Classs.stream()
                .collect(Collectors.toMap(Class::getCodigo, Function.identity()));

        for (var entry : ClassEstudante.entrySet()) {
            Class Class = ClassMap.get(entry.getKey());
            if (Class == null)
                continue;

            for (String codEstudantes : entry.getValue()) {
                Class.adicionaEstudante(codEstudantes);
            }
        }

        ClassRepository.saveAll(Classs);
    }

    public List<RegistrationDTO> lerCSV(MultipartFile csv) throws IOException {
        try (InputStream stream = csv.getInputStream()) {
            String line;
            String delimiter = ",";
            List<RegistrationDTO> matriculas = new ArrayList<>();

            BufferedReader br = new BufferedReader(new InputStreamReader(stream));
            while ((line = br.readLine()) != null) {
                String[] data = line.split(delimiter);
                String codigo = data[0];
                String estudante = data[1];
                matriculas.add(new RegistrationDTO(codigo, estudante));
            }

            return matriculas;
        }
    }

    public List<RegistrationDTO> lerXLSX(MultipartFile xlsx) throws Exception {
        Workbook workbook = WorkbookFactory.create(xlsx.getInputStream());

        List<RegistrationDTO> matriculas = new ArrayList<>();

        for (int i = 0; i < workbook.getNumberOfSheets(); i++) {
            Sheet sheet = workbook.getSheetAt(i);

            for (Row row : sheet) {
                Cell cellCodClass = row.getCell(0);
                Cell cellCodEstudante = row.getCell(1);
                String codigo;
                String estudante;
                if (cellCodClass.getCellType() == CellType.STRING
                        && cellCodEstudante.getCellType() == CellType.STRING) {
                    codigo = cellCodClass.getRichStringCellValue().getString();
                    estudante = cellCodEstudante.getRichStringCellValue().getString();
                    matriculas.add(new RegistrationDTO(codigo, estudante));
                } else {
                    throw new Exception("XLSX com o formato errado");
                }
            }
        }
        return matriculas;
    }
}
