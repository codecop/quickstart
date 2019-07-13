// change all injectons to constructor
import java.lang.reflect.Modifier
import org.walkmod.javalang.ast.CompilationUnit
import org.walkmod.javalang.ast.body.ConstructorDeclaration
import org.walkmod.javalang.ast.body.FieldDeclaration
import org.walkmod.javalang.ast.body.ModifierSet
import org.walkmod.javalang.ast.body.TypeDeclaration
import org.walkmod.javalang.ast.stmt.BlockStmt
import org.walkmod.javalang.ast.expr.MarkerAnnotationExpr
import org.walkmod.javalang.ast.expr.NameExpr

import groovy.inspect.Inspector.MemberComparator


def processFile(CompilationUnit file) {
    // println(file.fileName)
    // println(file.getPackage())
    file.types.each { processType(it) }
}

def processType(TypeDeclaration type) {
    injectedFields = findInjectedFields(type)
    if (!injectedFields) {
        return
    }

    println("--- class " + type.name)
    // println(injectedFields)
    // injectedFields.each { removeInjectAnnotation(it) }

    constructors = findConstuctors(type)
    constructor = null
    if (!constructors) {
        constructor = createConstructor(type.name)
        type.members.add(0, constructor)
    } else {
        constructor = constructors[0]
    }
    // println(constructor)
    if (! constructor.annotations) {
        constructor.annotations = []
    }
    
    if (! constructor.annotations.any { it.name.name == "Inject" }) {
        constructor.annotations.add(new MarkerAnnotationExpr(new NameExpr("Inject")))
    }

    // add argument to list
    // add setting to body
}

def findInjectedFields(TypeDeclaration type) {
    return type.members.
            findAll { it instanceof FieldDeclaration }.
            findAll { it.annotations.any { it.name.name == "Inject" } }
}

def removeInjectAnnotation(FieldDeclaration field) {
    field.annotations = field.annotations.findAll { it.name.name != "Inject" }
}

def findConstuctors(TypeDeclaration type) {
    return type.members.
            findAll { it instanceof ConstructorDeclaration }
}

def createConstructor(name) {
    modifiers = ModifierSet.PUBLIC
    constructor = new ConstructorDeclaration(modifiers, name)
    constructor.block = new BlockStmt()
    return constructor
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
