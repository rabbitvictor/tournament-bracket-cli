package algorithms

import model.Player

data class Node(
    var parent: Node? = null,
    var left: Node? = null,
    var right: Node? = null,
    var player: Player? = null,
    val height: Int,
) {
    override fun toString(): String {
        return "node height = $height"
    }
}

class BracketTree(
    val height: Int,
    val root: Node
) {
    val bracket = buildTree(root, height).toList()
}

private fun buildTree(currentNode: Node, treeHeight: Int, currentHeight: Int = 0, tree: MutableList<Node> = mutableListOf()): MutableList<Node> {
    val nextHeight = currentHeight + 1
    val (leftNode, rightNode) = createChildren(currentNode, nextHeight, tree)

    // Stop Condition: current height is equal as the height of the tree being built
    if (treeHeight == currentHeight) return tree

    // Step: create children for the next height in the tree for both children from current node
    buildTree(leftNode, treeHeight, nextHeight, tree)
    buildTree(rightNode, treeHeight, nextHeight, tree)

    return tree
}

private fun createChildren(
    currentNode: Node,
    nextHeight: Int,
    tree: MutableList<Node>
): Pair<Node, Node> {
    val leftNode = Node(parent = currentNode, height = nextHeight)
    val rightNode = Node(parent = currentNode, height = nextHeight)

    currentNode.apply {
        left = leftNode
        right = rightNode
    }

    tree += currentNode
    return Pair(leftNode, rightNode)
}

fun main() {
    val treeHeight = 10
    val root = Node(height = 0)
    val tree = buildTree(root, treeHeight, 0, mutableListOf())
    val size = tree.filter { it.height == treeHeight - 1}.size
    println("outer leaves size: $size")
}