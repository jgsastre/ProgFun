package patmat

import org.scalatest.FunSuite

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

import patmat.Huffman._

@RunWith(classOf[JUnitRunner])
class HuffmanSuite extends FunSuite {
  trait TestTrees {
    val t1 = Fork(Leaf('a',2), Leaf('b',3), List('a','b'), 5)
    val t2 = Fork(Fork(Leaf('a',2), Leaf('b',3), List('a','b'), 5), Leaf('d',4), List('a','b','d'), 9)
  }

  test("weight of a larger tree") {
    new TestTrees {
      assert(weight(t1) === 5)
    }
  }

  test("chars of a larger tree") {
    new TestTrees {
      assert(chars(t2) === List('a','b','d'))
    }
  }

  test("string2chars(\"hello, world\")") {
    assert(string2Chars("hello, world") === List('h', 'e', 'l', 'l', 'o', ',', ' ', 'w', 'o', 'r', 'l', 'd'))
  }

  test("makeOrderedLeafList for some frequency table") {
    assert(makeOrderedLeafList(List(('t', 2), ('e', 1), ('x', 3))) === List(Leaf('e',1), Leaf('t',2), Leaf('x',3)))
  }

  test("combine of some leaf list") {
    val leaflist = List(Leaf('e', 1), Leaf('t', 2), Leaf('x', 4))
    assert(combine(leaflist) === List(Fork(Leaf('e',1),Leaf('t',2),List('e', 't'),3), Leaf('x',4)))
  }

  test("decode and encode a very short text should be identity") {
    new TestTrees {
      assert(decode(t1, encode(t1)("ab".toList)) === "ab".toList)
    }
  }
  
  test("decode and quick encode a very short text should be identity") {
  new TestTrees {
      assert(decode(t1, quickEncode(t1)("ab".toList)) === "ab".toList)
    }
  }
  
  test("test times function") {
    println(times(string2Chars("hello, world")))
  }
  
  test("test times of some word") {
    assert(times(string2Chars("abrkdabrkabraba")) === List(('a',5), ('b',4), ('r',3), ('k',2), ('d',1)))
  }
  
  test("combine of a singleton or nil") {
    combine(Nil)
  }
  
  test("quick encode gives the correct byte sequence") {
    val testSentence = "ture from 45 BC, making it over 2000 years old. Richard Mc"
    val codeTree = createCodeTree(testSentence.toList)
    assert(encode(codeTree)(testSentence.toList) === quickEncode(codeTree)(testSentence.toList))
  }
  
  test("decode and quick encode is identity") {
    val testSentence = "ture from 45 BC, making it over 2000 years old. Richard Mc"
    val codeTree = createCodeTree(testSentence.toList)
    assert(decode(codeTree, quickEncode(codeTree)(testSentence.toList)) === 
      List('t', 'u', 'r', 'e', ' ', 'f', 'r', 'o', 'm', ' ', '4', '5', ' ', 'B', 'C', ',', ' ', 'm', 'a', 'k', 'i', 'n', 'g', ' ', 'i', 't', ' ', 'o', 'v', 'e', 'r', ' ', '2', '0', '0', '0', ' ', 'y', 'e', 'a', 'r', 's', ' ', 'o', 'l', 'd', '.', ' ', 'R', 'i', 'c', 'h', 'a', 'r', 'd', ' ', 'M', 'c'))
  }
  
  
  
}
