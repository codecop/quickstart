// change all injectons to constructor
import org.walkmod.javalang.ast.CompilationUnit
import org.walkmod.javalang.ast.body.TypeDeclaration
import org.walkmod.walkers.VisitorContext

import groovy.inspect.Inspector.MemberComparator

import org.walkmod.query.QueryEngine

// available bindings:
// org.walkmod.javalang.ast.CompilationUnit node;
// org.walkmod.walkers.VisitorContext context;
// org.walkmod.query.QueryEngine query;

def findInjectedFields(TypeDeclaration type) {
    return type.members.
        findAll { it instanceof org.walkmod.javalang.ast.body.FieldDeclaration }.
        findAll { it.annotations.any { it.name.name == "Inject" } }
}

def cu(CompilationUnit node) {
    // println(node.fileName)
    // println(node.getPackage())
    for(type in node.types) {
        println("--- class " + type.name)
        println(findInjectedFields(type))
//  println(member.type);
//  println(member.variables);
//  member.annotations = [] // drop annotation

        println("")
    }
}

def vc(VisitorContext context) {
    println(context.entrySet())
}

println("Hello from Script")
cu(node)
// vc(context)
