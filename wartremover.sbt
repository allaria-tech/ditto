// add any rule you want to enable
wartremoverErrors ++= Seq(Wart.TripleQuestionMark)

// comment any rule you want to enable
// See https://www.wartremover.org/doc/warts.html for more details.
wartremoverWarnings ++= Warts.allBut(
  // Wart.ArrayEquals, // Don't use List ==, use sameElements instead. Unlike other collections == on arrays and iterators checks reference equality.
  Wart.Any, // Any is the top type; it is the supertype of every other type. The Scala compiler loves to infer Any as a generic type, but that is almost always incorrect. Explicit type arguments should be used instead.
  Wart.AnyVal, // Idem any.
  Wart.AsInstanceOf, // asInstanceOf is unsafe in isolation and violates parametricity when guarded by isInstanceOf. Refactor so that the desired type is proven statically.
  Wart.DefaultArguments, // Scala allows methods to have default arguments, which make it hard to use methods as functions.
  Wart.EitherProjectionPartial, // See https://www.wartremover.org/doc/warts.html for more details.
  Wart.Enumeration, // Scala’s Enumeration can cause performance problems due to its reliance on reflection. Additionally, the lack of exhaustive match checks and partial methods can lead to runtime errors. Instead of Enumeration, a sealed abstract class extended by case objects should be used instead.
  Wart.Equals, // Don't use `==` method - use === or equivalent instead. See https://www.wartremover.org/doc/warts.html
  // Wart.ExplicitImplicitTypes, // Implicit definitions must have an explicit type.
  // Wart.FinalCaseClass, // Scala’s final case classes provide a useful implementation of logicless data types. Extending a final case class can break this functionality in surprising ways. This can be avoided by always making them final or sealed.
  // Wart.FinalVal,               // Don't use final val - use non-final val or final def or add.
  Wart.GlobalExecutionContext, // Don't use ExecutionContext.global.
  Wart.ImplicitConversion, // Implicit conversions weaken type safety and always can be replaced by explicit conversions.
  Wart.ImplicitParameter, // Implicit parameters as configuration often lead to confusing interfaces and can result in surprising inconsistencies.
  Wart.IsInstanceOf, // isInstanceOf violates parametricity. Refactor so that the type is established statically.
  Wart.JavaConversions, // The standard library provides implicits conversions to and from Java types in scala.collection.JavaConversions. This can make code difficult to understand and read about. The explicit conversions provided by scala.collection.JavaConverters should be used instead.
  Wart.JavaSerializable, // java.io.Serializable is a common subtype to many structures, especially those imported from Java. For example, String is a subtype of java.io.Serializable but not scala.Serializable. The Scala compiler loves to infer java.io.Serializable as a common supertype, but that is almost always incorrect. Explicit type arguments should be used instead.
  // Wart.LeakingSealed, // Descendants of a sealed type must be final or sealed. Otherwise this type can be extended in another file through its descendant.
  // Wart.ListAppend, // Don't use List `:+` method because too slow.
  // Wart.MutableDataStructures, // The standard library provides mutable collections. Mutation breaks equational reasoning.
  Wart.NonUnitStatements, // Scala allows statements to return any type. Statements should only return Unit (this ensures that they’re really intended to be statements).
  Wart.Nothing, // Nothing is a special bottom type; it is a subtype of every other type. The Scala compiler loves to infer Nothing as a generic type but that is almost always incorrect. Explicit type arguments should be used instead.
  // Wart.NoNeedImport, // The wildcard import exists. Remove other explicitly names of the `import`.
  Wart.Null,        // null is a special value that inhabits all reference types. It breaks type safety.
  // Wart.Option2Iterable, // Scala inserts an implicit conversion from Option to Iterable. This can hide bugs and creates surprising situations like Some(1) zip Some(2) returning an Iterable[(Int, Int)].
  // Wart.OptionPartial, // scala.Option has a get method which will throw if the value is None. The program should be refactored to use scala.Option#fold to explicitly handle both the Some and None cases.
  Wart.Overloading, // Method overloading may lead to confusion and usually can be avoided.
  Wart.PlatformDefault, // please specify locale parameter. don't use platform's default locale https://docs.oracle.com/javase/8/docs/api/java/lang/String.html
  Wart.Product, // Product is a type common to many structures; it is the supertype of final case classes and tuples. The Scala compiler loves to infer Product as a generic type, but that is almost always incorrect. Explicit type arguments should be used instead.
  Wart.PublicInference, // Type inference of public members can expose extra type information, that can break encapsulation.
  // Wart.Recursion, // General recursion can result in non-termination. There are various techniques, like fixed-point combinators, that allow you to extract recursion from your code. Recursion can also cause problems with stack usage. This can often be fixed with a @tailrec annotation (which uses constant stack) or by using a trampoline (which moves stack usage to the heap).
  // Wart.Return, // return breaks referential transparency. Refactor to terminate computations in a safe way.
  Wart.Serializable, // Serializable is a type common to many structures. The Scala compiler loves to infer Serializable as a generic type, but that is almost always incorrect. Explicit type arguments should be used instead.
  Wart.StringPlusAny, // Scala’s String interface provides a + method that converts the operand to a String via its toString method. As mentioned in the documentation for the ToString wart, this method is unreliable and brittle.
  Wart.Throw, // throw implies partiality. Encode exceptions/errors as return values instead using Either.
  Wart.ToString, // Scala creates a toString method automatically for all classes. Since toString is based on the class name, any rename can potentially introduce bugs. This is especially pernicious for case objects. toString should be explicitly overridden wherever used.
  // Wart.IterableOps, // See https://www.wartremover.org/doc/warts.html for more details.
  // Wart.TripleQuestionMark // ??? throws NotImplementedError. Encode exceptions/errors as return values instead using Either.
  // Wart.TryPartial, // scala.util.Try has a get method which will throw if the value is a Failure. The program should be refactored to use scala.util.Try#map and scala.util.Try#getOrElse to explicitly handle both the Success and Failure cases.
  // Wart.Var,  // Mutable variables are a source of bugs. Refactor to use immutable values.
  // Wart.While // while loop usually indicates low-level code. If performance is not an issue, it can be replaced.
)
