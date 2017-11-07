package lesson3

import org.junit.Test

import org.junit.Assert.*
import java.util.TreeSet

class BinaryTreeTest {
    @Test
    fun add() {
        val tree = BinaryTree<Int>()
        tree.add(10)
        tree.add(5)
        tree.add(7)
        tree.add(10)
        assertEquals(3, tree.size)
        assertTrue(tree.contains(5))

        tree.add(3)
        tree.add(1)
        tree.add(3)
        tree.add(4)
        assertEquals(6, tree.size)
        assertFalse(tree.contains(8))

        tree.add(8)
        tree.add(15)
        tree.add(15)
        tree.add(20)
        assertEquals(9, tree.size)
        assertTrue(tree.contains(8))
        assertTrue(tree.checkInvariant())
        assertEquals(1, tree.first())
        assertEquals(20, tree.last())
    }

    @Test
    fun remove(){
        val tree = BinaryTree<Int>()
        tree.add(10)
        tree.add(5)
        tree.add(11)
        tree.add(18)
        tree.remove( 10)
        assertFalse(tree.contains(10))
        assertEquals(3, tree.size)

        tree.add(14)
        tree.add(13)
        tree.add(12)
        tree.remove( 11)
        assertFalse(tree.contains(11))
        assertEquals(5, tree.size)

        val tree1 = BinaryTree<Int>()
        tree1.add(20)
        tree1.add(10)
        tree1.add(5)
        tree1.add(11)
        tree1.add(18)
        tree1.remove( 10)
        assertFalse(tree1.contains(10))
        assertEquals(4, tree1.size)

        tree1.add(14)
        tree1.add(13)
        tree1.add(12)
        tree1.remove( 11)
        assertFalse(tree1.contains(11))
        assertEquals(6, tree1.size)

        val tree2 = BinaryTree<Int>()
        tree2.add(20)
        tree2.add(10)
        tree2.add(25)
        tree2.remove(20)
        assertFalse(tree2.contains(20))
    }

    @Test
    fun headSet(){
        val tree = BinaryTree<Int>()
        tree.add(10)
        tree.add(5)
        tree.add(12)
        tree.add(18)
        tree.add(4)
        tree.add(9)
        tree.add(29)
        val result = TreeSet<Int>()
        result.add(10)
        result.add(5)
        result.add(4)
        result.add(9)
        assertEquals(result, tree.headSet(11))
    }

    @Test
    fun tailSet(){
        val tree = BinaryTree<Int>()
        tree.add(10)
        tree.add(5)
        tree.add(12)
        tree.add(18)
        tree.add(4)
        tree.add(9)
        tree.add(29)
        val result = TreeSet<Int>()
        result.add(29)
        result.add(18)
        result.add(12)
        assertEquals(result, tree.tailSet(12))
    }

    @Test
    fun addKotlin() {
        val tree = KtBinaryTree<Int>()
        tree.add(10)
        tree.add(5)
        tree.add(7)
        tree.add(10)
        assertEquals(3, tree.size)
        assertTrue(tree.contains(5))
        tree.add(3)
        tree.add(1)
        tree.add(3)
        tree.add(4)
        assertEquals(6, tree.size)
        assertFalse(tree.contains(8))
        tree.add(8)
        tree.add(15)
        tree.add(15)
        tree.add(20)
        assertEquals(9, tree.size)
        assertTrue(tree.contains(8))
        assertTrue(tree.checkInvariant())
        assertEquals(1, tree.first())
        assertEquals(20, tree.last())
    }
}