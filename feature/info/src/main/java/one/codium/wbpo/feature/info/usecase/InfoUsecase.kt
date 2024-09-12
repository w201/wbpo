package one.codium.wbpo.feature.info.usecase

import one.codium.wbpo.feature.info.entity.SystemInfo

fun interface InfoUsecase {
    operator fun invoke(): SystemInfo
}
