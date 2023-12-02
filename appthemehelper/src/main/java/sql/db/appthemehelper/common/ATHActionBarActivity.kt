package code.name.monkey.appthemehelper.common

import androidx.appcompat.widget.Toolbar
import sql.db.appthemehelper.common.ATHToolbarActivity
import sql.db.appthemehelper.util.ToolbarContentTintHelper


class ATHActionBarActivity : ATHToolbarActivity() {

    override fun getATHToolbar(): Toolbar? {
        return ToolbarContentTintHelper.getSupportActionBarView(supportActionBar)
    }
}
