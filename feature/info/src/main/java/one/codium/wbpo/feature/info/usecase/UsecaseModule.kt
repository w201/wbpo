package one.codium.wbpo.feature.info.usecase

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object UsecaseModule {

    @Provides
    @Singleton
    fun getInfoUsecase():InfoUsecase = InfoUsecaseImpl()

}
