package org.example.model.milestones

enum class Chapter(val order: Short, val file: String, var reached: Boolean = false) : IMilestone {
    PREAMBLE(0, "preamble", true),
    CHAPTER1(1, "chapter1"),
    CHAPTER2(2, "chapter2"),
    CHAPTER3(3, "chapter3"),
    CHAPTER4(4, "chapter4"),
    CHAPTER5(5, "chapter5"),
    CHAPTER6(6, "chapter6"),
    CHAPTER7(7, "chapter7");

    fun getChaptersUntilThis(): List<Chapter> {
        return entries.filter { it.order < this.order }.sortedBy { it.order }
    }


    override fun isReached(): Boolean = reached
    val chapterContent: String by lazy {
        Chapter::class.java.getResource("/chapters/$file.txt")!!.readText()
    }
}