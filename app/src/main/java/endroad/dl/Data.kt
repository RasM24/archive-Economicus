package endroad.dl

import android.content.Context
import com.google.firebase.database.FirebaseDatabase
import endroad.dl.models.Enterprise
import endroad.dl.models.Link
import org.json.JSONArray
import java.util.*

val databaseReference = FirebaseDatabase.getInstance().reference ?: throw NullPointerException()
val factoryList = ArrayList<Enterprise>()
val linkList = ArrayList<Link>()

fun Context.loadEnterpriseArrayFromAssets() {
	runCatching {
		val jsonArray = JSONArray(getStringFromAssetFile("factory"))
		factoryList.clear()
		for (i in 0 until jsonArray.length()) {
			factoryList.add(Enterprise.fromJSON(jsonArray.getJSONObject(i)))
		}
	}
}

fun Context.loadLinkArrayFromAssets() {
	runCatching {
		val jsonArray = JSONArray(getStringFromAssetFile("link"))
		linkList.clear()
		for (i in 0 until jsonArray.length()) {
			linkList.add(Link.fromJSON(jsonArray.getJSONObject(i)))
		}
	}
}

private fun Context.getStringFromAssetFile(name: String): String {
	runCatching {
		assets.open(name).run {
			val size = available()
			val buffer = ByteArray(size)
			read(buffer)
			close()
			return String(buffer)
		}
	}
	return ""
}