package hgu.isel.tokenizer;

import hgu.isel.structure.attribute.AttributeInformation;
import hgu.isel.structure.constant.ConstantPoolInformation;
import hgu.isel.structure.constant.type.UTF8Information;
import hgu.isel.structure.field.FieldInformation;
import hgu.isel.structure.interfaces.Interfaces;
import hgu.isel.structure.method.MethodInformation;

import java.nio.file.StandardOpenOption;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * The purpose of the ByteTokenizer class is tokenize input bytecodes.
 * <p>
 * All getters and setters in this class are simple property accessors with no side effects.
 */
public class ByteTokenizer {


    private HashSet<String> tokens = new HashSet<>(); // used to generate vocabulary


    /**
     * This method generates tokens by using the ByteStructure parameter.
     * @param byteStructure This parameter contains the entire structure of the input bytecodes.
     * @return It returns the size of the tokens to check the current number of the tokens.
     */
    public int saveTokens(ByteStructure byteStructure) {
        tokens.addAll(tokenize4vocab(byteStructure));

        return tokens.size();
    }

    /**
     * It generates vocabulary to the filePath. It uses tokens and makes txt file.
     */
    public void createVocabulary(String vocabPath) { // create vocabulary

        List<String> list = new ArrayList<>(tokens);

        try {
            Files.write(Paths.get(vocabPath), list);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * This method writes files with only constant pool and method structures. There are many other structures, but the purpose of this method focuses on only constant pool and method structures.
     * @param filePath The generated file's path.
     * @param constantPool The entire instances of the constant pool.
     * @param method The entire instances of the method structure.
     */
    public void writeFiles(String filePath, List<String> constantPool, List<String> method) {
        try {
            Files.write(Paths.get(filePath), constantPool, StandardOpenOption.CREATE, StandardOpenOption.APPEND);
            Files.write(Paths.get(filePath), method, StandardOpenOption.CREATE, StandardOpenOption.APPEND);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * This method writes files with only method structures. There are many other structures, but the purpose of this method focuses on only method structures.
     * @param filePath The generated file's path.
     * @param method The entire instances of the method structure.
     */
    public void writeFiles(String filePath, List<String> method) {
        System.out.println(filePath);
        String content = String.join(" ", method);
        try {
            Files.writeString(
                    Paths.get(filePath),
                    content,
                    StandardOpenOption.CREATE,
                    StandardOpenOption.APPEND
            );
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * This method generates new tokenized bytecode txt files with constant pool.
     * @param byteStructure It contains the result of analyzing bytecode process.
     */
    public void generateNewFilesWithConstantPool(ByteStructure byteStructure, String outputDirectory) {
        List<String> constantPools = new ArrayList<>();
        List<String> methods = new ArrayList<>();

        File file = new File(byteStructure.getFileName());
        String fileName = file.getName();
        String fileNameWithoutExtension = fileName.replaceFirst("\\.class&", "");

        String outputFile = outputDirectory + fileNameWithoutExtension;

        for(int i = 0; i < byteStructure.getConstantPoolInformation().length; i++) {
            if(byteStructure.getConstantPoolInformation()[i] != null) {
                constantPools.addAll(byteStructure.getConstantPoolInformation()[i].tokenize());
            }
        }


        constantPools.add("<EOC>"); // End of Constant pool token

        for(int i = 0; i < byteStructure.getMethodInformation().length; i++) {
            methods.addAll(byteStructure.getMethodInformation()[i].tokenize());
            String inputFileName = outputFile + "_" + i + ".txt";

            if(constantPools.size() + methods.size() > 512) {
                return;
            }

            writeFiles(inputFileName, constantPools, methods);
            methods.clear();
        }
    }

    /**
     * This method generates new tokenized bytecode txt files without constant pool.
     * It focuses on only method structure, so the other structures are ignored.
     * @param byteStructure It contains the result of analyzing bytecode process.
     */
    public void generateNewFiles(ByteStructure byteStructure, String outputDirectory) {
        List<String> methods = new ArrayList<>();

        File file = new File(byteStructure.getFileName());
        String fileName = file.getName();
        String fileNameWithoutExtension = fileName.replaceFirst("\\.class&", "");

        String outputFile = outputDirectory + fileNameWithoutExtension;

        for(int i = 0; i < byteStructure.getMethodInformation().length; i++) {
            methods.addAll(byteStructure.getMethodInformation()[i].tokenize());
            String inputFileName = outputFile + "_" + i + ".txt";

            writeFiles(inputFileName, methods);
            methods.clear();
        }
    }

    /**
     * By using the name of the method, it can find a section of specific method.
     * @param byteStructure It contains the result of analyzing bytecode process.
     * @param methodName It is a name of the target method.
     */
    public void findSpecificMethod(ByteStructure byteStructure, String methodName, String outputDirectory) {
        File file = new File(byteStructure.getFileName());
        String fileName = file.getName();
        String fileNameWithoutExtension = fileName.replaceFirst("\\.class&?$", "");

        String outputFile = outputDirectory + "/" + fileNameWithoutExtension;



        for(int i = 0; i < byteStructure.getMethodInformation().length; i++) {
            List<String> methods = new ArrayList<>();

            methods.addAll(byteStructure.getMethodInformation()[i].tokenize());
            String inputFileName = outputFile + "_" + i + "_" + methodName + ".txt";

            byte[] index = byteStructure.getMethodInformation()[i].getNameIndex();

            ConstantPoolInformation constantPoolInformation = byteStructure.getConstantPoolInformation()[(((index[0] & 0xFF) << 8) | (index[1] & 0xFF)) - 1];

            if(constantPoolInformation instanceof UTF8Information) {
                String targetMethodName = new String(((UTF8Information) constantPoolInformation).getBytes());


                if(targetMethodName.equals(methodName)) {

                    byte[] descriptorIndex = byteStructure.getMethodInformation()[i].getDescriptorIndex();

                    ConstantPoolInformation descriptorInformation = byteStructure.getConstantPoolInformation()[((descriptorIndex[0] & 0xFF) << 8) | (descriptorIndex[1] & 0xFF) - 1];
                    if(descriptorInformation instanceof UTF8Information) {
                        String targetDescriptorName = new String(((UTF8Information) descriptorInformation).getBytes());


                        methods.add(targetDescriptorName);
                        writeFiles(inputFileName, methods);
                    }

                }
            }

        }
    }

    /**
     * If there are so many constant pool values, it might not be used to training process due to lack of method instructions.
     * For this reason, this method automatically removes byteStructures which have bigger size of constant pool than 400.
     * @param byteStructure It contains the result of analyzing bytecode process.
     * @return It returns boolean values to check whether input byteStructure is removed or not.
     */
    public boolean removeFiles(ByteStructure byteStructure) {
        List<String> constantPools = new ArrayList<>();

        for(int i = 0; i < byteStructure.getConstantPoolInformation().length; i++) {
            if(byteStructure.getConstantPoolInformation()[i] != null) {
                constantPools.addAll(byteStructure.getConstantPoolInformation()[i].tokenize());
            }
        }

        if(constantPools.size() > 480) { // maximum token length
            return true;
        } else {
            return false;
        }
    }

    // used to tokenize option t

    /**
     * This method is used to tokenize option t.
     * It retrieves the entire structures of the bytecode, then put values into the ArrayList.
     * @param byteStructure It contains the result of analyzing bytecode process.
     * @return It returns List which contains Strings.
     */
    public List<String> tokenize(ByteStructure byteStructure) { // translate input to tokenized one
        List<String> inputs = new ArrayList<>();
        List<String> methods = new ArrayList<>();
        StringBuilder stringBuilder = new StringBuilder();

         // tokenize magic number
         inputs.add("[Magic Number]");
         for(byte b : byteStructure.getMagic()) {
             stringBuilder.append(String.format("%02X", b));
         }
         inputs.add(stringBuilder.toString());
         stringBuilder.setLength(0);

         // tokenize minor version
         inputs.add("[Minor Version]");
         for(byte b : byteStructure.getMinorVersion()) {
             stringBuilder.append(String.format("%02X", b));
         }
         inputs.add(stringBuilder.toString());
         stringBuilder.setLength(0);

         // tokenize major version
         inputs.add("[Major Version]");
         for(byte b : byteStructure.getMajorVersion()) {
             stringBuilder.append(String.format("%02X", b));
         }
         inputs.add(stringBuilder.toString());
         stringBuilder.setLength(0);

         // tokenize constant pool count
         inputs.add("[CP Count]");
         for(byte b : byteStructure.getConstantPoolCount()) {
             stringBuilder.append(String.format("%02X", b));
         }
         inputs.add(stringBuilder.toString());
         stringBuilder.setLength(0);

        // tokenize constant pool
        for(int i = 0; i < byteStructure.getConstantPoolInformation().length; i++) {
            if(byteStructure.getConstantPoolInformation()[i] != null) {
                inputs.addAll(byteStructure.getConstantPoolInformation()[i].tokenize());
            }
        }

         // tokenize access flag
         inputs.add("[Class Access Flag]");
         for(byte b : byteStructure.getAccessFlag()) {
             stringBuilder.append(String.format("%02X", b));
         }
         inputs.add(stringBuilder.toString());
         stringBuilder.setLength(0);

         // tokenize this class
         inputs.add("[This Class]");
         for(byte b : byteStructure.getThisClass()) {
             stringBuilder.append(String.format("%02X", b));
         }
         inputs.add(stringBuilder.toString());
         stringBuilder.setLength(0);

         // tokenize super class
         inputs.add("[Super Class]");
         for(byte b : byteStructure.getSuperClass()) {
             stringBuilder.append(String.format("%02X", b));
         }
         inputs.add(stringBuilder.toString());
         stringBuilder.setLength(0);

         // tokenize interface count
         inputs.add("[Interface Count]");
         for(byte b : byteStructure.getInterfacesCount()) {
             stringBuilder.append(String.format("%02X", b));
         }
         inputs.add(stringBuilder.toString());
         stringBuilder.setLength(0);

         // tokenize interface
         for(Interfaces i : byteStructure.getInterfaces()) {
             inputs.addAll(i.tokenize());
         }

         // tokenize fields count
         inputs.add("[Fields Count]");
         for(byte b : byteStructure.getFieldsCount()) {
             stringBuilder.append(String.format("%02X", b));
         }
         inputs.add(stringBuilder.toString());
         stringBuilder.setLength(0);

         // tokenize fields
         for(FieldInformation f : byteStructure.getFieldInformation()) {
             inputs.addAll(f.tokenize());
         }

         // tokenize methods count
         inputs.add("[Methods Count]");
         for(byte b : byteStructure.getMethodsCount()) {
             stringBuilder.append(String.format("%02X", b));
         }
         inputs.add(stringBuilder.toString());
         stringBuilder.setLength(0);
//
//         // tokenize methods
//         for(int i = 0; i < byteStructure.getMethodInformation()) {
//             methods.addAll(byteStructure.getMethodInformation[i].tokenize());
//             String fileName = byteStructure.getFileName() + "_" + i;
//
//             writeFiles(fileName, inputs, methods);
//             methods.clear();
//         }

        for(MethodInformation m : byteStructure.getMethodInformation()) {
            methods.addAll(m.tokenize());

            for(String s : inputs) {
                System.out.println(s);
            }
            for(String s : methods) {
                System.out.println(s);
            }

            System.out.println("[marker]");
            methods.clear();
        }
        

         // tokenize attributes count
         inputs.add("[Class Attributes Count]");
         for(byte b : byteStructure.getAttributesCount()) {
             stringBuilder.append(String.format("%02X", b));
         }
         inputs.add(stringBuilder.toString());
         stringBuilder.setLength(0);

         // tokenize Attributes
         for(AttributeInformation a : byteStructure.getAttributeInformation()) {
             inputs.addAll(a.tokenize());
         }

        return inputs;
    }

    /**
     * This method is used to tokenize for generate vocabulary.
     * @param byteStructure It contains the result of analyzing bytecode process.
     * @return It returns List.
     */
    public List<String> tokenize4vocab(ByteStructure byteStructure) {

        List<String> inputs = new ArrayList<>();
        List<String> methods = new ArrayList<>();
        StringBuilder stringBuilder = new StringBuilder();

        for(int i = 0; i < byteStructure.getConstantPoolInformation().length; i++) {
            if(byteStructure.getConstantPoolInformation()[i] != null) {
                inputs.addAll(byteStructure.getConstantPoolInformation()[i].tokenize());
            }
        }

        for(MethodInformation m : byteStructure.getMethodInformation()) {
            inputs.addAll(m.tokenize());
        }

        return inputs;

    }

    public ByteTokenizer() {
    }

}
