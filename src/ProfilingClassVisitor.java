import jdk.internal.org.objectweb.asm.ClassVisitor;
import jdk.internal.org.objectweb.asm.MethodVisitor;
import jdk.internal.org.objectweb.asm.Opcodes;

public class ProfilingClassVisitor extends ClassVisitor implements Opcodes {

    private static final int API_VERSION = ASM5;

    ProfilingClassVisitor(ClassVisitor cv) {
        super(API_VERSION, cv);
    }

    @Override
    public MyMethodVisitor visitMethod(int access, String name, String desc, String signature, String[] exceptions){

        MethodVisitor mv = cv.visitMethod(access, name, desc, signature, exceptions);

        return new MyMethodVisitor(API_VERSION, mv, access, name, desc);

    }
}
