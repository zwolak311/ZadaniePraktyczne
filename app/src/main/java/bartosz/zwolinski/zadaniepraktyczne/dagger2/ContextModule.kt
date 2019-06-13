package zwolinski.bartosz.zadaniepraktyczne.dagger2

import android.content.Context
import dagger.Module
import dagger.Provides

@Module
class ContextModule(var context: Context) {

    @Provides
    fun context(): Context = context.applicationContext
}