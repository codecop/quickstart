// change all injectons to constructor
import java.lang.reflect.Modifier
import org.walkmod.javalang.ast.CompilationUnit
import org.walkmod.javalang.ast.body.ConstructorDeclaration
import org.walkmod.javalang.ast.body.FieldDeclaration
import org.walkmod.javalang.ast.body.ModifierSet
import org.walkmod.javalang.ast.body.TypeDeclaration
import org.walkmod.javalang.ast.stmt.BlockStmt
import org.walkmod.javalang.ast.stmt.ExpressionStmt
import org.walkmod.javalang.ast.expr.MarkerAnnotationExpr
import org.walkmod.javalang.ast.expr.NameExpr
import org.walkmod.javalang.ast.body.Parameter
import org.walkmod.javalang.ast.expr.AssignExpr
import org.walkmod.javalang.ast.expr.NameExpr
import org.walkmod.javalang.ast.expr.FieldAccessExpr
import org.walkmod.javalang.ast.expr.ThisExpr


injectAnnotation = new MarkerAnnotationExpr(new NameExpr("Inject"))

def processFile(CompilationUnit file) {
    // println("file " + file.fileName)
    file.types.each { processType(it) }
}

def processType(TypeDeclaration type) {
    // println("class " + type.name)

    injectedFields = findInjectedFields(type)
    if (!injectedFields) {
        return
    }
    // println(injectedFields)

    injectedFields.each { removeInjectAnnotation(it) }

    ConstructorDeclaration constructor = getConstuctor(type)
    if (! hasInjectAnnotation(constructor)) {
        constructor.annotations += injectAnnotation
    }

    injectedFields.each {
        constructor.parameters += new Parameter(it.type, it.variables[0].id)
        // TODO unhandled: Multiple fields in one variable declaration.

        assignment = createAssignment(it.variables[0].id.name)
        constructor.block.stmts += assignment
    }
    // println(constructor)
}

def findInjectedFields(TypeDeclaration type) {
    return type.members.
            findAll { it instanceof FieldDeclaration }.
            findAll { hasInjectAnnotation(it) }
}

def hasInjectAnnotation(body) {
    return body.annotations.any { it.name.name == "Inject" }
}

def removeInjectAnnotation(FieldDeclaration field) {
    field.annotations = field.annotations.findAll { it.name.name != "Inject" }
}

def getConstuctor(TypeDeclaration type) {
    constructors = findConstructors(type)
    if (!constructors) {
        constructor = createConstructor(type.name)
        type.members.add(constructor)
        // TODO Generated constructor added not after all fields.
    } else {
        constructor = constructors[0]
        // TODO unhandled: Multiple constructors to be modified.
    }

    constructor.annotations = constructor.annotations ?: []
    constructor.parameters = constructor.parameters ?: []

    markAsChanged(constructor)

    return constructor
}

def findConstructors(TypeDeclaration type) {
    return type.members.findAll { it instanceof ConstructorDeclaration }
}

def createConstructor(name) {
    modifiers = ModifierSet.PUBLIC
    constructor = new ConstructorDeclaration(modifiers, name)
    constructor.block = new BlockStmt()
    constructor.block.stmts = constructor.block.stmts ?: []
    return constructor
}

def markAsChanged(node) {
    node.beginLine = 0
    node.beginColumn = 0
    node.endLine = 0
    node.endColumn = 0
}

def createAssignment(fieldName) {
    field = new FieldAccessExpr(new ThisExpr(), fieldName)
    parameter = new NameExpr(fieldName)
    operator = AssignExpr.Operator.assign

    new ExpressionStmt(new AssignExpr(field, parameter, operator))
}

// available bindings:
// org.walkmod.javalang.ast.CompilationUnit node;
// org.walkmod.walkers.VisitorContext context;
// org.walkmod.query.QueryEngine query;

processFile(node)
