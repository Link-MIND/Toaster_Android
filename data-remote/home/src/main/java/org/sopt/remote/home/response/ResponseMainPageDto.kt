package org.sopt.remote.home.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.sopt.model.category.CategoryList
import org.sopt.model.home.MainPage

@Serializable
data class ResponseMainPageDto(
  @SerialName("nickname")
  val nickName: String,
  @SerialName("readToastNum")
  val readToastNum: Int,
  @SerialName("allToastNum")
  val allToastNum: Int,
  @SerialName("mainCateogoryListDto")
  val mainCategoryListDto: List<CategoryListDto>,
) {
  @Serializable
  data class CategoryListDto(
    @SerialName("categoryId")
    val categoryId: Long,
    @SerialName("categoryTitle")
    val categoryTitle: String,
    @SerialName("toastNum")
    val toastNum: Int,
  )
}

internal fun ResponseMainPageDto.toCoreModel() = MainPage(
  nickName = nickName,
  readToastNum = readToastNum,
  allToastNum = allToastNum,
  mainCategoryListDto = mainCategoryListDto.map { it.toCoreListModel() },
)

internal fun ResponseMainPageDto.CategoryListDto.toCoreListModel() =
  CategoryList(
    categoryId = categoryId,
    categoryTitle = categoryTitle,
    toastNum = toastNum,
  )
