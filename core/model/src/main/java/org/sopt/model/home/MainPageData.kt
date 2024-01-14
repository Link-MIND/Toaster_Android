package org.sopt.model.home

import org.sopt.model.category.CategoryList

data class MainPageData(
  val nickName: String,
  val readToastNum: Int,
  val allToastNum: Int,
  val mainCategoryListDto: List<CategoryList>,
)
