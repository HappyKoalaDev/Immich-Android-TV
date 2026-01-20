package nl.giejay.android.tv.immich.shared.prefs

import androidx.annotation.StringRes
import nl.giejay.android.tv.immich.ImmichApplication
import nl.giejay.android.tv.immich.R
import nl.giejay.android.tv.immich.api.model.Album

enum class AlbumsOrder(val sort: Comparator<Album>, @StringRes val titleResId: Int) : EnumWithTitle {
    ALPHABETICALLY_A_Z(compareBy { it.albumName }, R.string.order_alphabetically_az) {
        override fun getTitle(): String {
            return ImmichApplication.appContext!!.getString(titleResId)
        }
    },
    ALPHABETICALLY_Z_A(ALPHABETICALLY_A_Z.sort.reversed(), R.string.order_alphabetically_za) {
        override fun getTitle(): String {
            return ImmichApplication.appContext!!.getString(titleResId)
        }
    },
    LAST_UPDATED(compareByDescending { it.endDate }, R.string.albums_order_last_updated) {
        override fun getTitle(): String {
            return ImmichApplication.appContext!!.getString(titleResId)
        }
    },
    LEAST_UPDATED(LAST_UPDATED.sort.reversed(), R.string.albums_order_least_updated) {
        override fun getTitle(): String {
            return ImmichApplication.appContext!!.getString(titleResId)
        }
    },
    ASSET_COUNT(compareBy<Album> { it.assetCount }.reversed(), R.string.albums_order_asset_count) {
        override fun getTitle(): String {
            return ImmichApplication.appContext!!.getString(titleResId)
        }
    };

    companion object {
        fun valueOfSafe(name: String, default: AlbumsOrder): AlbumsOrder {
            return entries.find { it.toString() == name } ?: default
        }
    }
}
