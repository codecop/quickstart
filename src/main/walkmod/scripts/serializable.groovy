import javax.lang.model.element.Modifier

import org.walkmod.javalang.ASTManager
import com.squareup.javapoet.*

// see https://blog.walkmod.com/how-to-maintain-java-architectures-with-javapoet-and-walkmod-45611b1bc627

// User
String entityName = node.types[0].name

//com.example.model
String packageName = node.package.name.toString()

//HERE we create the Serializable entity with JavaPoet
TypeSpec entity = TypeSpec.classBuilder(entityName)
        .addModifiers(Modifier.PUBLIC)
        .addSuperinterface(Serializable.class).build()

JavaFile javaFile = JavaFile.builder(packageName, entity).build()

//HERE adding our results to WalkMod
context.addResultNode(ASTManager.parse(javaFile.toString(), true))
