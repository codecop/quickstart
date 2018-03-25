// log the context
import org.walkmod.javalang.ast.CompilationUnit
import org.walkmod.walkers.VisitorContext
import org.walkmod.query.QueryEngine

// available bindings:
// org.walkmod.javalang.ast.CompilationUnit node;
// org.walkmod.walkers.VisitorContext context;
// org.walkmod.query.QueryEngine query;

def cu(CompilationUnit node) {
    println(node.fileName)
    println(node.getPackage())
}

def vc(VisitorContext context) {
    println(context.entrySet())
}

println("Hello from Script")
cu(node)
vc(context)
