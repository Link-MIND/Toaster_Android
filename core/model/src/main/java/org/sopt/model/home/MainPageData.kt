package org.sopt.model.home

import org.sopt.model.category.Category

data class MainPageData(
  val nickName: String,
  val readToastNum: Int,
  val allToastNum: Int,
  val mainCategoryDto: List<Category>,
)
