// change all injectons to constructor
import java.lang.reflect.Modifier
import org.walkmod.javalang.ast.CompilationUnit
import org.walkmod.javalang.ast.body.ConstructorDeclaration
import org.walkmod.javalang.ast.body.FieldDeclaration
import org.walkmod.javalang.ast.body.ModifierSet
import org.walkmod.javalang.ast.body.TypeDeclaration
import org.walkmod.javalang.ast.stmt.BlockStmt

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
    // println(fields)
    // fields.each { removeAnnotation(it) }

    constructors = findConstuctors(type)
    println(constructors)
    if (!constructors) {
        modifiers = ModifierSet.PUBLIC
        constructor = new ConstructorDeclaration(modifiers, type.name)
        constructor.block = new BlockStmt();
        
        constructors.add(constructor)
        type.members.add(0, constructor)
    }

    // if none create one
    // add @Inject to constructor (if not there)
    // add argument to list
    // add setting to body
}

def findInjectedFields(TypeDeclaration type) {
    return type.members.
            findAll { it instanceof FieldDeclaration }.
            findAll { it.annotations.any { it.name.name == "Inject" } }
}

def removeAnnotation(FieldDeclaration field) {
    field.annotations = field.annotations.findAll { it.name.name != "Inject" }
}

def findConstuctors(TypeDeclaration type) {
    return type.members.
            findAll { it instanceof ConstructorDeclaration }
}

// available bindings:
// org.walkmod.javalang.ast.CompilationUnit node;
// org.walkmod.walkers.VisitorContext context;
// org.walkmod.query.QueryEngine query;

processFile(node)

/*
 * TODO bot handled cases:
 * Multiple fields in one variable declaration.
 * Multiple constructors to be modified.
 * Generated constructor not after all fields.
 */
