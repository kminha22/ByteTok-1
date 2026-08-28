package hgu.isel.structure.attribute.type.code;

import hgu.isel.structure.attribute.type.code.set.*;
import hgu.isel.structure.attribute.type.code.set.jump.JumpOffset;
import hgu.isel.structure.attribute.type.code.set.match.MatchOffsetPair;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * This class analyzes the code attributes.
 * All getters and setters in this class are simple property accessors with no side effects.
 */
public class CodeAttributeAnalyzer {

    private byte[] codes; // the entire bytes are in here
    private ArrayList<Instruction> instructions = new ArrayList<>(); // the entire instructions are in here
    private int offset;
    private int totalOffset;


    /**
     * The purpose of this method is analyzing the code attributes.
     * The code attribute is in the method information, and it contains the entire instructions. This method divides instructions by using the opcode.
     */
    public void analyze() {

        while(offset < codes.length) {
            byte format = codes[offset];
            int opcode = format & 0xFF;
            offset += 1;

            int currentOffset;
            int padding;
            byte[] paddingBytes;
            byte[] defaultByte;
            byte[] lowByte;
            byte[] highByte;
            JumpOffset[] jumpOffsets;
            int low;
            int high;

            Instruction instruction;
            byte index;
            byte index1;
            byte index2;
            byte branch1;
            byte branch2;
            byte branch3;
            byte branch4;
            byte constValue;
            byte count;
            byte dimensions;
            byte type;
            byte const1;
            byte const2;

            switch (opcode) {
                case 0x0:
                    // nop instruction
                    instruction = new DoNothingInstruction(format);
                    instructions.add(instruction);
                    break;
                case 0x1:
                    // aconst_null instruction
                    instruction = new PushNullInstruction(format);
                    instructions.add(instruction);
                    break;
                case 0x2:
                    // iconst_m1 instruction
                    instruction = new PushIntConstantInstruction(format);
                    instructions.add(instruction);
                    break;
                case 0x3:
                    // iconst_0 instruction
                    instruction = new PushIntConstantInstruction(format);
                    instructions.add(instruction);
                    break;
                case 0x4:
                    // iconst_1 instruction
                    instruction = new PushIntConstantInstruction(format);
                    instructions.add(instruction);
                    break;
                case 0x5:
                    // iconst21 instruction
                    instruction = new PushIntConstantInstruction(format);
                    instructions.add(instruction);
                    break;
                case 0x6:
                    // iconst_3 instruction
                    instruction = new PushIntConstantInstruction(format);
                    instructions.add(instruction);
                    break;
                case 0x7:
                    // iconst_4 instruction
                    instruction = new PushIntConstantInstruction(format);
                    instructions.add(instruction);
                    break;
                case 0x8:
                    // iconst_5 instruction
                    instruction = new PushIntConstantInstruction(format);
                    instructions.add(instruction);
                    break;
                case 0x9:
                    // lconst_0 instruction
                    instruction = new PushLongConstantInstruction(format);
                    instructions.add(instruction);
                    break;
                case 0xa:
                    // lconst_1 instruction
                    instruction = new PushLongConstantInstruction(format);
                    instructions.add(instruction);
                    break;
                case 0xb:
                    // fconst_0 instruction
                    instruction = new PushFloatInstruction(format);
                    instructions.add(instruction);
                    break;
                case 0xc:
                    // fconst_1 instruction
                    instruction = new PushFloatInstruction(format);
                    instructions.add(instruction);
                    break;
                case 0xd:
                    // fconst_2 instruction
                    instruction = new PushFloatInstruction(format);
                    instructions.add(instruction);
                    break;
                case 0xe:
                    // dconst_0 instruction
                    instruction = new PushDoubleInstruction(format);
                    instructions.add(instruction);
                    break;
                case 0xf:
                    // dconst_1 instruction
                    instruction = new PushDoubleInstruction(format);
                    instructions.add(instruction);
                    break;
                case 0x10:
                    // bipush instruction
                    index = codes[offset];
                    offset += 1;

                    instruction = new PushByteInstruction(format, index);
                    instructions.add(instruction);
                    break;
                case 0x11:
                    // sipush instruction
                    index1 = codes[offset];
                    offset += 1;

                    index2 = codes[offset];
                    offset += 1;

                    instruction = new PushShortInstruction(format, index1, index2);
                    instructions.add(instruction);
                    break;
                case 0x12:
                    // ldc instruction
                    index = codes[offset];
                    offset += 1;

                    instruction = new PushItemFromRunTimeConstantPoolInstruction(format, index);
                    instructions.add(instruction);
                    break;
                case 0x13:
                    // ldc_w instruction
                    index1 = codes[offset];
                    offset += 1;

                    index2 = codes[offset];
                    offset += 1;

                    instruction = new PushItemFromRunTimeConstantPoolWideIndexInstruction(format, index1, index2);
                    instructions.add(instruction);
                    break;
                case 0x14:
                    // ldc2_w instruction
                    index1 = codes[offset];
                    offset += 1;

                    index2 = codes[offset];
                    offset += 1;

                    instruction = new PushLongOrDoubleFromRunTimeConstantPoolWideIndexInstruction(format, index1, index2);
                    instructions.add(instruction);
                    break;
                case 0x15:
                    // iload
                    index = codes[offset];
                    offset += 1;

                    instruction = new LoadIntFromLocalVariableInstruction(format, index);
                    instructions.add(instruction);
                    break;
                case 0x16:
                    // lload instruction
                    index = codes[offset];
                    offset += 1;

                    instruction = new LoadLongFromLocalVariableInstruction(format, index);
                    instructions.add(instruction);
                    break;
                case 0x17:
                    // fload instruction
                    index = codes[offset];
                    offset += 1;

                    instruction = new LoadFloatFromLocalVariableInstruction(format, index);
                    instructions.add(instruction);
                    break;
                case 0x18:
                    // dload instruction
                    index = codes[offset];
                    offset += 1;

                    instruction = new LoadDoubleFromLocalVariableInstruction(format, index);
                    instructions.add(instruction);
                    break;
                case 0x19:
                    // aload instruction
                    index = codes[offset];
                    offset += 1;

                    instruction = new LoadReferenceFromLocalVariableInstruction(format, index);
                    instructions.add(instruction);
                    break;
                case 0x1a:
                    // iload_0 instruction
                    instruction = new LoadIntFromNLocalVariableInstruction(format);
                    instructions.add(instruction);
                    break;
                case 0x1b:
                    // iload_1 instruction
                    instruction = new LoadIntFromNLocalVariableInstruction(format);
                    instructions.add(instruction);
                    break;
                case 0x1c:
                    // iload_2 instruction
                    instruction = new LoadIntFromNLocalVariableInstruction(format);
                    instructions.add(instruction);
                    break;
                case 0x1d:
                    // iload_3 instruction
                    instruction = new LoadIntFromNLocalVariableInstruction(format);
                    instructions.add(instruction);
                    break;
                case 0x1e:
                    // lload_0 instruction
                    instruction = new LoadLongFromNLocalVariableInstruction(format);
                    instructions.add(instruction);
                    break;
                case 0x1f:
                    // lload_1 instruction
                    instruction = new LoadLongFromNLocalVariableInstruction(format);
                    instructions.add(instruction);
                    break;
                case 0x20:
                    // lload_2 instruction
                    instruction = new LoadLongFromNLocalVariableInstruction(format);
                    instructions.add(instruction);
                    break;
                case 0x21:
                    // lload_3 instruction
                    instruction = new LoadLongFromNLocalVariableInstruction(format);
                    instructions.add(instruction);
                    break;
                case 0x22:
                    // fload_0 instruction
                    instruction = new LoadFloatFromNLocalVariable(format);
                    instructions.add(instruction);
                    break;
                case 0x23:
                    // fload_1 instruction
                    instruction = new LoadFloatFromNLocalVariable(format);
                    instructions.add(instruction);
                    break;
                case 0x24:
                    // fload_2 instruction
                    instruction = new LoadFloatFromNLocalVariable(format);
                    instructions.add(instruction);
                    break;
                case 0x25:
                    // fload_3 instruction
                    instruction = new LoadFloatFromNLocalVariable(format);
                    instructions.add(instruction);
                    break;
                case 0x26:
                    // dload_0 instruction
                    instruction = new LoadDoubleFromNLocalVariableInstruction(format);
                    instructions.add(instruction);
                    break;
                case 0x27:
                    // dload_1 instruction
                    instruction = new LoadDoubleFromNLocalVariableInstruction(format);
                    instructions.add(instruction);
                    break;
                case 0x28:
                    // dload_2 instruction
                    instruction = new LoadDoubleFromNLocalVariableInstruction(format);
                    instructions.add(instruction);
                    break;
                case 0x29:
                    // dload_3 instruction
                    instruction = new LoadDoubleFromNLocalVariableInstruction(format);
                    instructions.add(instruction);
                    break;
                case 0x2a:
                    // aload_0 instruction
                    instruction = new LoadReferenceFromNLocalVariableInstruction(format);
                    instructions.add(instruction);
                    break;
                case 0x2b:
                    // aload_1 instruction
                    instruction = new LoadReferenceFromNLocalVariableInstruction(format);
                    instructions.add(instruction);
                    break;
                case 0x2c:
                    // aload_2 instruction
                    instruction = new LoadReferenceFromNLocalVariableInstruction(format);
                    instructions.add(instruction);
                    break;
                case 0x2d:
                    // aload_3 instruction
                    instruction = new LoadReferenceFromNLocalVariableInstruction(format);
                    instructions.add(instruction);
                    break;
                case 0x2e:
                    // iaload instruction
                    instruction = new LoadIntFromArrayInstruction(format);
                    instructions.add(instruction);
                    break;
                case 0x2f:
                    // laload instruction
                    instruction = new LoadLongFromArrayInstruction(format);
                    instructions.add(instruction);
                    break;
                case 0x30:
                    // faload instruction
                    instruction = new LoadFloatFromArrayInstruction(format);
                    instructions.add(instruction);
                    break;
                case 0x31:
                    // daload instruction
                    instruction = new LoadDoubleFromArrayInstruction(format);
                    instructions.add(instruction);
                    break;
                case 0x32:
                    // aaload instruction
                    instruction = new LoadReferenceFromArrayInstruction(format);
                    instructions.add(instruction);
                    break;
                case 0x33:
                    // baload instruction
                    instruction = new LoadByteOrBooleanFromArrayInstruction(format);
                    instructions.add(instruction);
                    break;
                case 0x34:
                    // caload instruction
                    instruction = new LoadCharFromArrayInstruction(format);
                    instructions.add(instruction);
                    break;
                case 0x35:
                    // saload instruction
                    instruction = new LoadShortFromArrayInstruction(format);
                    instructions.add(instruction);
                    break;
                case 0x36:
                    // istore instruction
                    index = codes[offset];
                    offset += 1;

                    instruction = new StoreIntIntoLocalVariableInstruction(format, index);
                    instructions.add(instruction);
                    break;
                case 0x37:
                    // lstore instruction
                    index = codes[offset];
                    offset += 1;

                    instruction = new StoreLongIntoLocalVariableInstruction(format, index);
                    instructions.add(instruction);
                    break;
                case 0x38:
                    // fstore instruction
                    index = codes[offset];
                    offset += 1;

                    instruction = new StoreFloatIntoLocalVariableInstruction(format, index);
                    instructions.add(instruction);
                    break;
                case 0x39:
                    // dstore instruction
                    index = codes[offset];
                    offset += 1;

                    instruction = new StoreDoubleIntoLocalVariableInstruction(format, index);
                    instructions.add(instruction);
                    break;
                case 0x3a:
                    // astore instruction
                    index = codes[offset];
                    offset += 1;

                    instruction = new StoreReferenceIntoLocalVariableInstruction(format, index);
                    instructions.add(instruction);
                    break;
                case 0x3b:
                    // istore_0 instruction
                    instruction = new StoreIntIntoNLocalVariableInstruction(format);
                    instructions.add(instruction);
                    break;
                case 0x3c:
                    // istore_1 instruction
                    instruction = new StoreIntIntoNLocalVariableInstruction(format);
                    instructions.add(instruction);
                    break;
                case 0x3d:
                    // istore_2 instruction
                    instruction = new StoreIntIntoNLocalVariableInstruction(format);
                    instructions.add(instruction);
                    break;
                case 0x3e:
                    // istore_3 instruction
                    instruction = new StoreIntIntoNLocalVariableInstruction(format);
                    instructions.add(instruction);
                    break;
                case 0x3f:
                    // lstore_0 instruction
                    instruction = new StoreLongIntoNLocalVariableInstruction(format);
                    instructions.add(instruction);
                    break;
                case 0x40:
                    // lstore_1 instruction
                    instruction = new StoreLongIntoNLocalVariableInstruction(format);
                    instructions.add(instruction);
                    break;
                case 0x41:
                    // lstore_2 instruction
                    instruction = new StoreLongIntoNLocalVariableInstruction(format);
                    instructions.add(instruction);
                    break;
                case 0x42:
                    // lstore_3 instruction
                    instruction = new StoreLongIntoNLocalVariableInstruction(format);
                    instructions.add(instruction);
                    break;
                case 0x43:
                    // fstore_0 instruction
                    instruction = new StoreFloatIntoNLocalVariableInstruction(format);
                    instructions.add(instruction);
                    break;
                case 0x44:
                    // fstore_1 instruction
                    instruction = new StoreFloatIntoNLocalVariableInstruction(format);
                    instructions.add(instruction);
                    break;
                case 0x45:
                    // fstore_2 instruction
                    instruction = new StoreFloatIntoNLocalVariableInstruction(format);
                    instructions.add(instruction);
                    break;
                case 0x46:
                    // fstore_3 instruction
                    instruction = new StoreFloatIntoNLocalVariableInstruction(format);
                    instructions.add(instruction);
                    break;
                case 0x47:
                    // dstore_0 instruction
                    instruction = new StoreDoubleIntoNLocalVariableInstruction(format);
                    instructions.add(instruction);
                    break;
                case 0x48:
                    // dstore_1 instruction
                    instruction = new StoreDoubleIntoNLocalVariableInstruction(format);
                    instructions.add(instruction);
                    break;
                case 0x49:
                    // dstore_2 instruction
                    instruction = new StoreDoubleIntoNLocalVariableInstruction(format);
                    instructions.add(instruction);
                    break;
                case 0x4a:
                    // dstore_3 instruction
                    instruction = new StoreDoubleIntoNLocalVariableInstruction(format);
                    instructions.add(instruction);
                    break;
                case 0x4b:
                    // astore_0 instruction
                    instruction = new StoreReferenceIntoNLocalVariableInstruction(format);
                    instructions.add(instruction);
                    break;
                case 0x4c:
                    // astore_1 instruction
                    instruction = new StoreReferenceIntoNLocalVariableInstruction(format);
                    instructions.add(instruction);
                    break;
                case 0x4d:
                    // astore_2 instruction
                    instruction = new StoreReferenceIntoNLocalVariableInstruction(format);
                    instructions.add(instruction);
                    break;
                case 0x4e:
                    // astore_3 instruction
                    instruction = new StoreReferenceIntoNLocalVariableInstruction(format);
                    instructions.add(instruction);
                    break;
                case 0x4f:
                    // iastore instruction
                    instruction = new StoreIntoIntArrayInstruction(format);
                    instructions.add(instruction);
                    break;
                case 0x50:
                    // lastore instruction
                    instruction = new StoreIntoLongArrayInstruction(format);
                    instructions.add(instruction);
                    break;
                case 0x51:
                    // fastore instruction
                    instruction = new StoreIntoFloatArrayInstruction(format);
                    instructions.add(instruction);
                    break;
                case 0x52:
                    // dastore instruction
                    instruction = new StoreIntoDoubleArrayInstruction(format);
                    instructions.add(instruction);
                    break;
                case 0x53:
                    // aastore instruction
                    instruction = new StoreIntoReferenceArrayInstruction(format);
                    instructions.add(instruction);
                    break;
                case 0x54:
                    // bastore instruction
                    instruction = new StoreIntoByteOrBooleanArrayInstruction(format);
                    instructions.add(instruction);
                    break;
                case 0x55:
                    // castore instruction
                    instruction = new StoreIntoCharArrayInstruction(format);
                    instructions.add(instruction);
                    break;
                case 0x56:
                    // sastore instruction
                    instruction = new StoreIntoShortArrayInstruction(format);
                    instructions.add(instruction);
                    break;
                case 0x57:
                    // pop instruction
                    instruction = new PopInstruction(format);
                    instructions.add(instruction);
                    break;
                case 0x58:
                    // pop2 instruction
                    instruction = new Pop2Instruction(format);
                    instructions.add(instruction);
                    break;
                case 0x59:
                    // dup instruction
                    instruction = new DuplicateTopOperandStackValueInstruction(format);
                    instructions.add(instruction);
                    break;
                case 0x5a:
                    // dup_x1 instruction
                    instruction = new DuplicateTopOperandStackValueAndInsertTwoValuesInstruction(format);
                    instructions.add(instruction);
                    break;
                case 0x5b:
                    // dup_x2 instruction
                    instruction = new DuplicateTopOperandStackValueAndInsertThreeValuesInstruction(format);
                    instructions.add(instruction);
                    break;
                case 0x5c:
                    // dup2 instruction
                    instruction = new DuplicateTopOneOrTwoOperandInstruction(format);
                    instructions.add(instruction);
                    break;
                case 0x5d:
                    // dup2_x1 instruction
                    instruction = new DuplicateTopOneOrTwoOperandAndInsertTwoOrThreeValuesInstruction(format);
                    instructions.add(instruction);
                    break;
                case 0x5e:
                    // dup2_x2 instruction
                    instruction = new DuplicateTopOneOrTwoOperandAndInsertThreeOrFourValuesInstruction(format);
                    instructions.add(instruction);
                    break;
                case 0x5f:
                    // swap instruction
                    instruction = new SwapInstruction(format);
                    instructions.add(instruction);
                    break;
                case 0x60:
                    // iadd instruction
                    instruction = new AddIntInstruction(format);
                    instructions.add(instruction);
                    break;
                case 0x61:
                    // ladd instruction
                    instruction = new AddLongInstruction(format);
                    instructions.add(instruction);
                    break;
                case 0x62:
                    // fadd instruction
                    instruction = new AddFloatInstruction(format);
                    instructions.add(instruction);
                    break;
                case 0x63:
                    // dadd instruction
                    instruction = new AddDoubleInstruction(format);
                    instructions.add(instruction);
                    break;
                case 0x64:
                    // isub instruction
                    instruction = new SubtractIntInstruction(format);
                    instructions.add(instruction);
                    break;
                case 0x65:
                    // lsub instruction
                    instruction = new SubtractLongInstruction(format);
                    instructions.add(instruction);
                    break;
                case 0x66:
                    // fsub instruction
                    instruction = new SubtractFloatInstruction(format);
                    instructions.add(instruction);
                    break;
                case 0x67:
                    // dsub instruction
                    instruction = new SubtractDoubleInstruction(format);
                    instructions.add(instruction);
                    break;
                case 0x68:
                    // imul instruction
                    instruction = new MultiplyIntInstruction(format);
                    instructions.add(instruction);
                    break;
                case 0x69:
                    // lmul instruction
                    instruction = new MultiplyLongInstruction(format);
                    instructions.add(instruction);
                    break;
                case 0x6a:
                    // fmul instruction
                    instruction = new MultiplyFloatInstruction(format);
                    instructions.add(instruction);
                    break;
                case 0x6b:
                    // dmul instruction
                    instruction = new MultiplyDoubleInstruction(format);
                    instructions.add(instruction);
                    break;
                case 0x6c:
                    // idiv instruction
                    instruction = new DivideIntInstruction(format);
                    instructions.add(instruction);
                    break;
                case 0x6d:
                    // ldiv instruction
                    instruction = new DivideLongInstruction(format);
                    instructions.add(instruction);
                    break;
                case 0x6e:
                    // fdiv instruction
                    instruction = new DivideFloatInstruction(format);
                    instructions.add(instruction);
                    break;
                case 0x6f:
                    // ddiv instruction
                    instruction = new DivideDoubleInstruction(format);
                    instructions.add(instruction);
                    break;
                case 0x70:
                    // irem instruction
                    instruction = new RemainderIntInstruction(format);
                    instructions.add(instruction);
                    break;
                case 0x71:
                    // lrem instruction
                    instruction = new RemainderLongInstruction(format);
                    instructions.add(instruction);
                    break;
                case 0x72:
                    // frem instruction
                    instruction = new RemainderFloatInstruction(format);
                    instructions.add(instruction);
                    break;
                case 0x73:
                    // drem instruction
                    instruction = new RemainderDoubleInstruction(format);
                    instructions.add(instruction);
                    break;
                case 0x74:
                    // ineg instruction
                    instruction = new NegateIntInstruction(format);
                    instructions.add(instruction);
                    break;
                case 0x75:
                    // lneg instruction
                    instruction = new NegateLongInstruction(format);
                    instructions.add(instruction);
                    break;
                case 0x76:
                    // fneg instruction
                    instruction = new NegateFloatInstruction(format);
                    instructions.add(instruction);
                    break;
                case 0x77:
                    // dneg instruction
                    instruction = new NegateDoubleInstruction(format);
                    instructions.add(instruction);
                    break;
                case 0x78:
                    // ishl instruction
                    instruction = new ShiftLeftIntInstruction(format);
                    instructions.add(instruction);
                    break;
                case 0x79:
                    // lshl instruction
                    instruction = new ShiftLeftLongInstruction(format);
                    instructions.add(instruction);
                    break;
                case 0x7a:
                    // ishr instruction
                    instruction = new ArithmeticShiftRightIntInstruction(format);
                    instructions.add(instruction);
                    break;
                case 0x7b:
                    // lshr instruction
                    instruction = new ArithmeticShiftRightLongInstruction(format);
                    instructions.add(instruction);
                    break;
                case 0x7c:
                    // iushr instruction
                    instruction = new LogicalShiftRightIntInstruction(format);
                    instructions.add(instruction);
                    break;
                case 0x7d:
                    // lushr instruction
                    instruction = new LogicalShiftRightLongInstruction(format);
                    instructions.add(instruction);
                    break;
                case 0x7e:
                    // iand instruction
                    instruction = new BooleanANDIntInstruction(format);
                    instructions.add(instruction);
                    break;
                case 0x7f:
                    // land instruction
                    instruction = new BooleanANDLongInstruction(format);
                    instructions.add(instruction);
                    break;
                case 0x80:
                    // ior instruction
                    instruction = new BooleanORIntInstruction(format);
                    instructions.add(instruction);
                    break;
                case 0x81:
                    // lor instruction
                    instruction = new BooleanORLongInstruction(format);
                    instructions.add(instruction);
                    break;
                case 0x82:
                    // ixor instruction
                    instruction = new BooleanXORIntInstruction(format);
                    instructions.add(instruction);
                    break;
                case 0x83:
                    // lxor instruction
                    instruction = new BooleanXORLongInstruction(format);
                    instructions.add(instruction);
                    break;
                case 0x84:
                    // iinc instruction
                    index = codes[offset];
                    offset += 1;

                    constValue = codes[offset];
                    offset += 1;

                    instruction = new IncrementLocalVariableByConstantInstruction(format, index, constValue);
                    instructions.add(instruction);
                    break;
                case 0x85:
                    // i2l instruction
                    instruction = new ConvertIntToLongInstruction(format);
                    instructions.add(instruction);
                    break;
                case 0x86:
                    // i2f instruction
                    instruction = new ConvertIntToFloatInstruction(format);
                    instructions.add(instruction);
                    break;
                case 0x87:
                    // i2d instruction
                    instruction = new ConvertIntToDoubleInstruction(format);
                    instructions.add(instruction);
                    break;
                case 0x88:
                    // l2i instruction
                    instruction = new ConvertLongToIntInstruction(format);
                    instructions.add(instruction);
                    break;
                case 0x89:
                    // l2f instruction
                    instruction = new ConvertLongToFloatInstruction(format);
                    instructions.add(instruction);
                    break;
                case 0x8a:
                    // l2d instruction
                    instruction = new ConvertLongToDoubleInstruction(format);
                    instructions.add(instruction);
                    break;
                case 0x8b:
                    // f2i instruction
                    instruction = new ConvertFloatToIntInstruction(format);
                    instructions.add(instruction);
                    break;
                case 0x8c:
                    // f2l instruction
                    instruction = new ConvertFloatToLongInstruction(format);
                    instructions.add(instruction);
                    break;
                case 0x8d:
                    // f2d instruction
                    instruction = new ConvertFloatToDoubleInstruction(format);
                    instructions.add(instruction);
                    break;
                case 0x8e:
                    // d2i instruction
                    instruction = new ConvertDoubleToIntInstruction(format);
                    instructions.add(instruction);
                    break;
                case 0x8f:
                    // d2l instruction
                    instruction = new ConvertDoubleToLongInstruction(format);
                    instructions.add(instruction);
                    break;
                case 0x90:
                    // d2f instruction
                    instruction = new ConvertDoubleToFloatInstruction(format);
                    instructions.add(instruction);
                    break;
                case 0x91:
                    // i2b instruction
                    instruction = new ConvertIntToByteInstruction(format);
                    instructions.add(instruction);
                    break;
                case 0x92:
                    // i2c instruction
                    instruction = new ConvertIntToCharInstruction(format);
                    instructions.add(instruction);
                    break;
                case 0x93:
                    // i2s instruction
                    instruction = new ConvertIntToShortInstruction(format);
                    instructions.add(instruction);
                    break;
                case 0x94:
                    // lcmp instruction
                    instruction = new CompareLongInstruction(format);
                    instructions.add(instruction);
                    break;
                case 0x95:
                    // fcmpl instruction
                    instruction = new CompareFloatInstruction(format);
                    instructions.add(instruction);
                    break;
                case 0x96:
                    // fcmpg instruction
                    instruction = new CompareFloatInstruction(format);
                    instructions.add(instruction);
                    break;
                case 0x97:
                    // dcmp<op> instruction with dcmpl
                    instruction = new CompareDoubleInstruction(format);
                    instructions.add(instruction);
                    break;
                case 0x98:
                    // dcmp<op> instruction with dcmpg
                    instruction = new CompareDoubleInstruction(format);
                    instructions.add(instruction);
                    break;
                case 0x99:
                    // ifeq instruction
                    branch1 = codes[offset];
                    offset += 1;

                    branch2 = codes[offset];
                    offset += 1;

                    instruction = new BranchIfIntComparisonWithZeroSucceedsInstruction(format, branch1, branch2);
                    instructions.add(instruction);
                    break;
                case 0x9a:
                    // ifne instruction
                    branch1 = codes[offset];
                    offset += 1;

                    branch2 = codes[offset];
                    offset += 1;

                    instruction = new BranchIfIntComparisonWithZeroSucceedsInstruction(format, branch1, branch2);
                    instructions.add(instruction);
                    break;
                case 0x9b:
                    // iflt instruction
                    branch1 = codes[offset];
                    offset += 1;

                    branch2 = codes[offset];
                    offset += 1;

                    instruction = new BranchIfIntComparisonWithZeroSucceedsInstruction(format, branch1, branch2);
                    instructions.add(instruction);
                    break;
                case 0x9c:
                    // ifge instruction
                    branch1 = codes[offset];
                    offset += 1;

                    branch2 = codes[offset];
                    offset += 1;

                    instruction = new BranchIfIntComparisonWithZeroSucceedsInstruction(format, branch1, branch2);
                    instructions.add(instruction);
                    break;
                case 0x9d:
                    // ifgt instruction
                    branch1 = codes[offset];
                    offset += 1;

                    branch2 = codes[offset];
                    offset += 1;

                    instruction = new BranchIfIntComparisonWithZeroSucceedsInstruction(format, branch1, branch2);
                    instructions.add(instruction);
                    break;
                case 0x9e:
                    // ifle instruction
                    branch1 = codes[offset];
                    offset += 1;

                    branch2 = codes[offset];
                    offset += 1;

                    instruction = new BranchIfIntComparisonWithZeroSucceedsInstruction(format, branch1, branch2);
                    instructions.add(instruction);
                    break;
                case 0x9f:
                    // if_icmpeq instruction
                    branch1 = codes[offset];
                    offset += 1;

                    branch2 = codes[offset];
                    offset += 1;

                    instruction = new BranchIfIntComparisonSucceedsInstruction(format, branch1, branch2);
                    instructions.add(instruction);
                    break;
                case 0xa0:
                    // if_icmpne instruction
                    branch1 = codes[offset];
                    offset += 1;

                    branch2 = codes[offset];
                    offset += 1;

                    instruction = new BranchIfIntComparisonSucceedsInstruction(format, branch1, branch2);
                    instructions.add(instruction);
                    break;
                case 0xa1:
                    // if_icmplt instruction
                    branch1 = codes[offset];
                    offset += 1;

                    branch2 = codes[offset];
                    offset += 1;

                    instruction = new BranchIfIntComparisonSucceedsInstruction(format, branch1, branch2);
                    instructions.add(instruction);
                    break;
                case 0xa2:
                    // if_icmpge instruction
                    branch1 = codes[offset];
                    offset += 1;

                    branch2 = codes[offset];
                    offset += 1;

                    instruction = new BranchIfIntComparisonSucceedsInstruction(format, branch1, branch2);
                    instructions.add(instruction);
                    break;
                case 0xa3:
                    // if_icmpgt instruction
                    branch1 = codes[offset];
                    offset += 1;

                    branch2 = codes[offset];
                    offset += 1;

                    instruction = new BranchIfIntComparisonSucceedsInstruction(format, branch1, branch2);
                    instructions.add(instruction);
                    break;
                case 0xa4:
                    // if_icmple instruction
                    branch1 = codes[offset];
                    offset += 1;

                    branch2 = codes[offset];
                    offset += 1;

                    instruction = new BranchIfIntComparisonSucceedsInstruction(format, branch1, branch2);
                    instructions.add(instruction);
                    break;
                case 0xa5:
                    // if_acmpeq instruction
                    branch1 = codes[offset];
                    offset += 1;

                    branch2 = codes[offset];
                    offset += 1;

                    instruction = new BranchIfReferenceComparisonSucceedsInstruction(format, branch1, branch2);
                    instructions.add(instruction);
                    break;
                case 0xa6:
                    // if_acmpne instruction
                    branch1 = codes[offset];
                    offset += 1;

                    branch2 = codes[offset];
                    offset += 1;

                    instruction = new BranchIfReferenceComparisonSucceedsInstruction(format, branch1, branch2);
                    instructions.add(instruction);
                    break;
                case 0xa7:
                    // goto instruction
                    index1 = codes[offset];
                    offset += 1;

                    index2 = codes[offset];
                    offset += 1;

                    instruction = new BranchAlwaysInstruction(format, index1, index2);
                    instructions.add(instruction);
                    break;
                case 0xa8:
                    // jsr instruction
                    branch1 = codes[offset];
                    offset += 1;

                    branch2 = codes[offset];
                    offset += 1;

                    instruction = new JumpSubroutineInstruction(format, branch1, branch2);
                    instructions.add(instruction);
                    break;
                case 0xa9:
                    // ret instruction
                    index = codes[offset];
                    offset += 1;

                    instruction = new ReturnFromSubroutineInstruction(format, index);
                    instructions.add(instruction);
                    break;
                case 0xaa:
                    // tableswitch instruction

                    padding = (4 - (offset % 4)) % 4;
                    paddingBytes = Arrays.copyOfRange(codes, offset, offset + padding);


                    offset += padding; // padding

                    defaultByte = Arrays.copyOfRange(codes, offset, offset + 4);
                    offset += 4;

                    lowByte = Arrays.copyOfRange(codes, offset, offset + 4);
                    offset += 4;

                    highByte = Arrays.copyOfRange(codes, offset, offset + 4);
                    offset += 4;

                    low = ((lowByte[0]) << 24) |
                            ((lowByte[1] & 0xFF) << 16) |
                            ((lowByte[2] & 0xFF) << 8) |
                            (lowByte[3] & 0xFF);

                    high = ((highByte[0]) << 24) |
                            ((highByte[1] & 0xFF) << 16) |
                            ((highByte[2] & 0xFF) << 8) |
                            (highByte[3] & 0xFF);


                    int jumpOffset = high - low + 1;

                    jumpOffsets = new JumpOffset[jumpOffset];

                    for(int i = 0; i < jumpOffset; i++) {
                        byte[] info = Arrays.copyOfRange(codes, offset, offset + 4);
                        jumpOffsets[i] = new JumpOffset(info);
                        offset += 4;
                    }

                    instruction = new TableSwitchInstruction(format, paddingBytes, defaultByte, lowByte, highByte, jumpOffsets);
                    instructions.add(instruction);
                    break;
                case 0xab:
                    // lookupswitch instruction

                    padding = (4 - (offset % 4)) % 4;


                    paddingBytes = Arrays.copyOfRange(codes, offset, offset + padding);

                    offset += padding; // padding

                    defaultByte = Arrays.copyOfRange(codes, offset, offset + 4);
                    offset += 4;



                    byte[] pair = Arrays.copyOfRange(codes, offset, offset + 4);
                    offset += 4;


                    int pairCount = ((pair[0]) << 24) |
                            ((pair[1] & 0xFF) << 16) |
                            ((pair[2] & 0xFF) << 8) |
                            (pair[3] & 0xFF);

                    MatchOffsetPair[] matchOffsetPairs = new MatchOffsetPair[pairCount];


                    for(int i = 0; i < pairCount; i++) {


                        byte[] match = Arrays.copyOfRange(codes, offset, offset + 4);
                        offset += 4;

                        byte[] matchOffset = Arrays.copyOfRange(codes, offset, offset + 4);
                        offset += 4;

                        matchOffsetPairs[i] = new MatchOffsetPair(match, matchOffset);
                    }

                    instruction = new LookUpSwitchInstruction(format, paddingBytes, defaultByte, pair, matchOffsetPairs);
                    instructions.add(instruction);
                    break;
                case 0xac:
                    // ireturn instruction
                    instruction = new ReturnIntFromMethodInstruction(format);
                    instructions.add(instruction);
                    break;
                case 0xad:
                    // lreturn instruction
                    instruction = new ReturnLongFromMethodInstruction(format);
                    instructions.add(instruction);
                    break;
                case 0xae:
                    // freturn instruction
                    instruction = new ReturnFloatFromMethodInstruction(format);
                    instructions.add(instruction);
                    break;
                case 0xaf:
                    // dreturn instruction
                    instruction = new ReturnDoubleFromMethodInstruction(format);
                    instructions.add(instruction);
                    break;
                case 0xb0:
                    // areturn instruction
                    instruction = new ReturnReferenceFromMethodInstruction(format);
                    instructions.add(instruction);
                    break;
                case 0xb1:
                    // return instruction
                    instruction = new ReturnVoidFromMethodInstruction(format);
                    instructions.add(instruction);
                    break;
                case 0xb2:
                    // getstatic instruction
                    index1 = codes[offset];
                    offset += 1;

                    index2 = codes[offset];
                    offset += 1;

                    instruction = new GetStaticFieldFromClassInstruction(format, index1, index2);
                    instructions.add(instruction);
                    break;
                case 0xb3:
                    // putstatic instruction
                    index1 = codes[offset];
                    offset += 1;

                    index2 = codes[offset];
                    offset += 1;

                    instruction = new PutStaticInstruction(format, index1, index2);
                    instructions.add(instruction);
                    break;
                case 0xb4:
                    // getfield instruction
                    index1 = codes[offset];
                    offset += 1;

                    index2 = codes[offset];
                    offset += 1;

                    instruction = new FetchFieldFromObjectInstruction(format, index1, index2);
                    instructions.add(instruction);
                    break;
                case 0xb5:
                    // putfield instruction
                    index1 = codes[offset];
                    offset += 1;

                    index2 = codes[offset];
                    offset += 1;

                    instruction = new PutFieldInstruction(format, index1, index2);
                    instructions.add(instruction);
                    break;
                case 0xb6:
                    // invokevirtual instruction
                    index1 = codes[offset];
                    offset += 1;

                    index2 = codes[offset];
                    offset += 1;

                    instruction = new InvokeVirtualInstruction(format, index1, index2);
                    instructions.add(instruction);
                    break;
                case 0xb7:
                    // invokespecial instruction
                    index1 = codes[offset];
                    offset += 1;

                    index2 = codes[offset];
                    offset += 1;

                    instruction = new InvokeSpecialInstruction(format, index1, index2);
                    instructions.add(instruction);
                    break;
                case 0xb8:
                    // invokestatic instruction
                    index1 = codes[offset];
                    offset += 1;

                    index2 = codes[offset];
                    offset += 1;

                    instruction = new InvokeStaticInstruction(format, index1, index2);
                    instructions.add(instruction);
                    break;
                case 0xb9:
                    // invokeinterface instruction
                    index1 = codes[offset];
                    offset += 1;

                    index2 = codes[offset];
                    offset += 1;

                    count = codes[offset];
                    offset += 1;

                    offset += 1; // for blank byte

                    instruction = new InvokeInterfaceInstruction(format, index1, index2, count);
                    instructions.add(instruction);
                    break;
                case 0xba:
                    // invokedynamic instruction
                    index1 = codes[offset];
                    offset += 1;

                    index2 = codes[offset];
                    offset += 1;

                    offset += 2; // for blank bytes
                    instruction = new InvokeDynamicInstruction(format, index1, index2);
                    instructions.add(instruction);
                    break;
                case 0xbb:
                    // new instruction
                    index1 = codes[offset];
                    offset += 1;

                    index2 = codes[offset];
                    offset += 1;

                    instruction = new NewInstruction(format, index1, index2);
                    instructions.add(instruction);
                    break;
                case 0xbc:
                    // newarray instruction
                    type = codes[offset];
                    offset += 1;

                    instruction = new NewArrayInstruction(format, type);
                    instructions.add(instruction);
                    break;
                case 0xbd:
                    // anewarray instruction
                    index1 = codes[offset];
                    offset += 1;

                    index2 = codes[offset];
                    offset += 1;

                    instruction = new CreateNewReferenceArrayInstruction(format, index1, index2);
                    instructions.add(instruction);
                    break;
                case 0xbe:
                    // arraylength instruction
                    instruction = new ArrayLengthInstruction(format);
                    instructions.add(instruction);
                    break;
                case 0xbf:
                    // athrow instruction
                    instruction = new ThrowExceptionInstruction(format);
                    instructions.add(instruction);
                    break;
                case 0xc0:
                    // checkcast instruction
                    index1 = codes[offset];
                    offset += 1;

                    index2 = codes[offset];
                    offset += 1;

                    instruction = new CheckObjectTypeInstruction(format, index1, index2);
                    instructions.add(instruction);
                    break;
                case 0xc1:
                    // instanceof instruction
                    index1 = codes[offset];
                    offset += 1;

                    index2 = codes[offset];
                    offset += 1;

                    instruction = new InstanceOfInstruction(format, index1, index2);
                    instructions.add(instruction);
                    break;
                case 0xc2:
                    // monitorenter instruction
                    instruction = new MonitorEnterInstruction(format);
                    instructions.add(instruction);
                    break;
                case 0xc3:
                    // monitorexit instruction
                    instruction = new MonitorExitInstruction(format);
                    instructions.add(instruction);
                    break;
                case 0xc4:
                    //wide
                    byte wideOpcode = codes[offset];
                    int opcodeInt = wideOpcode & 0xFF;
                    offset += 1;

                    if(opcodeInt == 0x84) {
                        index1 = codes[offset];
                        offset += 1;

                        index2 = codes[offset];
                        offset += 1;

                        const1 = codes[offset];
                        offset += 1;

                        const2 = codes[offset];
                        offset += 1;

                        instruction = new WideWithIINCInstruction(format, wideOpcode, index1, index2, const1, const2);
                        instructions.add(instruction);
                        break;
                    } else {
                        index1 = codes[offset];
                        offset += 1;

                        index2 = codes[offset];
                        offset += 1;

                        instruction = new WideInstruction(format, wideOpcode, index1, index2);
                        instructions.add(instruction);
                        break;
                    }
                case 0xc5:
                    // multianewarray instruction
                    index1 = codes[offset];
                    offset += 1;

                    index2 = codes[offset];
                    offset += 1;

                    dimensions = codes[offset];
                    offset += 1;

                    instruction = new CreateMultidimensionalArrayInstruction(format, index1, index2, dimensions);
                    instructions.add(instruction);
                    break;
                case 0xc6:
                    // ifnull instruction
                    branch1 = codes[offset];
                    offset += 1;

                    branch2 = codes[offset];
                    offset += 1;

                    instruction = new BranchIfReferenceIsNullInstruction(format, branch1, branch2);
                    instructions.add(instruction);
                    break;
                case 0xc7:
                    // ifnonnull instruction
                    branch1 = codes[offset];
                    offset += 1;

                    branch2 = codes[offset];
                    offset += 1;

                    instruction = new BranchIfReferenceNotNullInstruction(format, branch1, branch2);
                    instructions.add(instruction);
                    break;
                case 0xc8:
                    // goto_w instruction
                    branch1 = codes[offset];
                    offset += 1;

                    branch2 = codes[offset];
                    offset += 1;

                    branch3 = codes[offset];
                    offset += 1;

                    branch4 = codes[offset];
                    offset += 1;

                    instruction = new BranchAlwaysWideIndexInstruction(format, branch1, branch2, branch3, branch4);
                    instructions.add(instruction);
                    break;
                case 0xc9:
                    // jsr_w instruction
                    branch1 = codes[offset];
                    offset += 1;

                    branch2 = codes[offset];
                    offset += 1;

                    branch3 = codes[offset];
                    offset += 1;

                    branch4 = codes[offset];
                    offset += 1;

                    instruction = new JumpSubroutineWideIndexInstruction(format, branch1, branch2, branch3, branch4);
                    instructions.add(instruction);
                    break;
                
                default:
                    throw new Error();

            }
        }

    }

    public CodeAttributeAnalyzer(byte[] codes, int totalOffset) {
        this.codes = codes;
        this.offset = 0;
        this.totalOffset = totalOffset;
    }

    public int getTotalOffset() {
        return totalOffset;
    }

    public void setTotalOffset(int totalOffset) {
        this.totalOffset = totalOffset;
    }

    public byte[] getCodes() {
        return codes;
    }

    public void setCodes(byte[] codes) {
        this.codes = codes;
    }

    public ArrayList<Instruction> getInstructions() {
        return instructions;
    }

    public void setInstructions(ArrayList<Instruction> instructions) {
        this.instructions = instructions;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }
}
