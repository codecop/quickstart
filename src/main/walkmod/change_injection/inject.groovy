// change all injectons to constructor
import org.walkmod.javalang.ast.CompilationUnit
import org.walkmod.javalang.ast.body.TypeDeclaration
import org.walkmod.javalang.ast.body.FieldDeclaration

import groovy.inspect.Inspector.MemberComparator


def processFile(CompilationUnit file) {
    // println(file.fileName)
    // println(file.getPackage())
    file.types.each { processType(it) }
}

def processType(TypeDeclaration type) {
    fields = findInjectedFields(type)
    if (!fields) {
        return
    }
    
    println("--- class " + type.name)
    println(fields)
    // fields.each { removeAnnotation(it) }
}

def findInjectedFields(TypeDeclaration type) {
    return type.members.
            findAll { it instanceof org.walkmod.javalang.ast.body.FieldDeclaration }.
            findAll { it.annotations.any { it.name.name == "Inject" } }
}

def removeAnnotation(FieldDeclaration field) {
    field.annotations = field.annotations.findAll { it.name.name != "Inject" }
}

// available bindings:
// org.walkmod.javalang.ast.CompilationUnit node;
// org.walkmod.walkers.VisitorContext context;
// org.walkmod.query.QueryEngine query;

processFile(node)
