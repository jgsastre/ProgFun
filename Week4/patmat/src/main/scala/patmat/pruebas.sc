package patmat

object pruebas {
  println("Welcome to the Scala worksheet 2")     //> Welcome to the Scala worksheet 2
  
  val nums = List(('a', 4), ('b', 2), ('c', 3))   //> nums  : List[(Char, Int)] = List((a,4), (b,2), (c,3))
  
  /*
  nums match {
  	case ('b', y) :: xs  => ('a', y + 1) :: xs
  	case x :: xs => x ::
  }
    */
  
  
  def insert(bag: List[(Char, Int)], char: Char) : List[(Char, Int)] = {
  	bag match {
    	case List() => List((char, 1))
    	case (`char`, x) :: xs => (char, x + 1) :: xs
    	case x :: xs => x :: insert(xs, char)
    }
  }                                               //> insert: (bag: List[(Char, Int)], char: Char)List[(Char, Int)]
  
  val newVal = insert(nums, 'a')                  //> newVal  : List[(Char, Int)] = List((a,5), (b,2), (c,3))
  insert(nums, 'd')                               //> res0: List[(Char, Int)] = List((a,4), (b,2), (c,3), (d,1))

  
  nums foreach println                            //> (a,4)
                                                  //| (b,2)
                                                  //| (c,3)



}