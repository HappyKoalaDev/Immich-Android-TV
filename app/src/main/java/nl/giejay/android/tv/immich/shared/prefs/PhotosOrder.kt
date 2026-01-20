package nl.giejay.android.tv.immich.shared.prefs

import androidx.annotation.StringRes
import nl.giejay.android.tv.immich.ImmichApplication
import nl.giejay.android.tv.immich.R
import nl.giejay.android.tv.immich.api.model.Asset
import nl.giejay.android.tv.immich.shared.util.Utils.compareToNullSafe

enum class PhotosOrder(val sort: Comparator<Asset>, @StringRes val titleResId: Int): EnumWithTitle {
    NEWEST_OLDEST(
        { a1, a2 ->
            (a2.exifInfo?.dateTimeOriginal ?: a2.fileModifiedAt)?.compareToNullSafe(
                a1.exifInfo?.dateTimeOriginal ?: a1.fileModifiedAt
            ) ?: 1
        },
        R.string.order_newest_oldest
    ) {
        override fun getTitle(): String {
            return ImmichApplication.appContext!!.getString(titleResId)
        }
    },
    OLDEST_NEWEST(
        { a1, a2 ->
            (a1.exifInfo?.dateTimeOriginal ?: a1.fileModifiedAt)?.compareToNullSafe(
                a2.exifInfo?.dateTimeOriginal ?: a2.fileModifiedAt
            ) ?: 1
        },
        R.string.photos_order_oldest_newest
    ) {
        override fun getTitle(): String {
            return ImmichApplication.appContext!!.getString(titleResId)
        }
    };

    companion object {
        fun valueOfSafe(name: String, default: PhotosOrder): PhotosOrder{
            return entries.find { it.toString() == name } ?: default
        }
    }
}
