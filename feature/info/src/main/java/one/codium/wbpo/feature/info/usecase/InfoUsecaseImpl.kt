package one.codium.wbpo.feature.info.usecase

import android.content.Context
import android.os.Build
import one.codium.wbpo.feature.info.entity.SystemInfo

class InfoUsecaseImpl : InfoUsecase {

    override fun invoke() = SystemInfo(
        Build.MANUFACTURER,
        Build.BRAND,
        Build.MODEL,
        Build.BOARD,
        Build.VERSION.RELEASE,
        "Sergii Volchenko"
    )

}
