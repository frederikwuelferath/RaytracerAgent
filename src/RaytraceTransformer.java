import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.security.ProtectionDomain;

import org.objectweb.asm.*;

public class RaytraceTransformer implements ClassFileTransformer {

    @Override
    public byte[] transform(ClassLoader loader, String className, Class<?> classBeingRedefined,
                            ProtectionDomain protectionDomain, byte[] classfileBuffer) throws IllegalClassFormatException {

        if("path/SDRaytracer".equals(className)) {
            try {
                ClassReader classReader = new ClassReader(classfileBuffer);
                ClassWriter classWriter = new ClassWriter(classReader, ClassWriter.COMPUTE_FRAMES | ClassWriter.COMPUTE_MAXS);

                ProfilingClassVisitor profilingClassVisitor = new ProfilingClassVisitor(classWriter);

                classReader.accept(profilingClassVisitor, ClassReader.EXPAND_FRAMES);
                return classWriter.toByteArray();
            } catch (Exception e) {
                e.printStackTrace();
                System.err.println(e.getMessage());
                return classfileBuffer;
            }
        }
        return classfileBuffer;
    }

}