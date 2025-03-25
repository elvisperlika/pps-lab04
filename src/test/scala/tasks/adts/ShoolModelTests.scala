package tasks.adts

import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.jupiter.api.BeforeEach

class SchoolModelTest:
    import tasks.adts.SchoolModel.*
    import tasks.adts.SchoolModel.BasicSchoolModule.*
    import u03.extensionmethods.Sequences.*
    import u03.extensionmethods.Sequences.Sequence.*

    val viroli = teacher("Viroli")
    val pps = course("PPS")

    @Test def testEmptySchool(): Unit =
        assertEquals(nil(), emptySchool.teachers)
        assertEquals(nil(), emptySchool.courses)

    @Test def testTeacherAndCourseCreation(): Unit =
        assertEquals("Viroli", viroli)
        assertEquals("PPS", pps)

    @Test def testSetTeacherToCourse(): Unit =
        assertEquals(Cons((viroli, pps), nil()), emptySchool.setTeacherToCourse(viroli, pps))

    @Test def testGetCoursesOfATeacher(): Unit =
        val oop = course("OOP")
        val pcd = course("PCD")
        val ricci = teacher("Ricci")
        val newSchool = emptySchool.setTeacherToCourse(viroli, oop)
            .setTeacherToCourse(viroli, pps)
            .setTeacherToCourse(ricci, pcd)
        assertEquals(Cons(oop, Cons(pps, Nil())), newSchool.coursesOfATeacher(viroli))


