package org.example.model.milestones

enum class Chapter(var reached: Boolean = false) : IMilestone {
    CHAPTER1,
    CHAPTER2,
    CHAPTER3,
    CHAPTER4,
    CHAPTER5,
    CHAPTER6,
    CHAPTER7;

    override fun isReached(): Boolean = reached
}