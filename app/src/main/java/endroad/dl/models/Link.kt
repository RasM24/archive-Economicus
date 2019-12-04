package endroad.dl.models

import org.json.JSONObject

data class Link(val name: String, val post: String, val url: String, val imagePath: String) {

	companion object {
		private const val NAME = "name"
		private const val POST = "post"
		private const val IMAGE_PATH = "imagePath"
		private const val LINK = "link"

		fun fromJSON(json: JSONObject): Link {
			val name = json.optString(NAME, "")
			val post = json.optString(POST, "")
			val imagePath = json.optString(IMAGE_PATH, "")
			val url = json.optString(LINK, "")

			return Link(name, post, url, imagePath)
		}
	}

}