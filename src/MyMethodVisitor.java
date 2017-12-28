import jdk.internal.org.objectweb.asm.MethodVisitor;
import jdk.internal.org.objectweb.asm.Opcodes;
import jdk.internal.org.objectweb.asm.commons.AdviceAdapter;


public class MyMethodVisitor extends AdviceAdapter implements Opcodes {

    protected MyMethodVisitor(int api, MethodVisitor mv, int access, String name, String desc) {

        super(api, mv, access, name, desc);

    }

    @Override
    public void visitMethodInsn(int opcode, String owner, String name, String desc, boolean itf) {

        mv.visitFieldInsn();
        mv.visitLdcInsn();
        mv.visitMethodInsn();

        super.visitMethodInsn(opcode, owner, name, desc, itf);

        return;

    }

}
