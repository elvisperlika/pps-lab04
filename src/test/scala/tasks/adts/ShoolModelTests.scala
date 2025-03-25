package tasks.adts

import org.junit.Assert.assertEquals
import org.junit.Test

class SchoolModelTest:
    import tasks.adts.SchoolModel.*
    import tasks.adts.SchoolModel.BasicSchoolModule.*
    import u03.extensionmethods.Sequences.*
    import u03.extensionmethods.Sequences.Sequence.*

    val school = emptySchool

    @Test def testEmptySchool(): Unit =
        assertEquals(nil(), school.teachers)
        assertEquals(nil(), school.courses)

