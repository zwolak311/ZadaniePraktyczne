package zwolinski.bartosz.zadaniepraktyczne.dagger2

import android.content.Context
import bartosz.zwolinski.zadaniepraktyczne.database.TTDDatabaseController
import dagger.Module
import dagger.Provides

@Module(includes = [ContextModule::class])
class TTDDatabaseControllerModule {

    @Provides
    fun tTDDatabaseController(context: Context): TTDDatabaseController {
        return TTDDatabaseController(context)
    }
}