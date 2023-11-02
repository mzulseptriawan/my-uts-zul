package com.mzulsept.myutszul

import com.google.gson.annotations.SerializedName

data class ResponseTekom(

	@field:SerializedName("data")
	val data: List<DataTekom?>? = null
)

data class DataTekom(

	@field:SerializedName("nim")
	val nim: Int? = null,

	@field:SerializedName("foto")
	val foto: String? = null,

	@field:SerializedName("gender")
	val gender: String? = null,

	@field:SerializedName("namaprodi")
	val namaprodi: String? = null
)
