
interface Celestial {
}

 class JEP409SealedClasses {
}

final class Planet implements Celestial {
}

final class Star implements Celestial {
}

final class Comet implements Celestial {
}

 abstract sealed class Shape
    permits Circle, Rectangle, Square, WeirdShape {  }

 final class Circle extends Shape {  }

 sealed class Rectangle extends Shape
    permits TransparentRectangle, FilledRectangle {  }
 final class TransparentRectangle extends Rectangle {  }
 final class FilledRectangle extends Rectangle {  }

 final class Square extends Shape {  }

 non-sealed class WeirdShape extends Shape {  }


abstract sealed class Root {
    final class A extends Root { }
    final class B extends Root { }
    final class C extends Root { }
}