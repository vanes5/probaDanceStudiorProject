import {Injectable, NotFoundException} from '@nestjs/common';
import {PrismaService} from "../prisma.service";
import {CreateCourseDto} from "./dto/CreateCourse.dto";
import {Course} from "../types/Course";

@Injectable()
export class ApiService {

  constructor(private readonly db: PrismaService) {
  }

  getCourses() {
    return this.db.courses.findMany()
  }

  async createCourse (createCourseDto: CreateCourseDto) {
    return this.db.courses.create({
      data: {
        ...createCourseDto,
        created_at: new Date(),
        updated_at: new Date()
      }
    })
  }

  async applyToCourse(id: number){
    const course = await this.db.courses.findUnique({
      where: {
        id
      }
    })

    if (course != null){
      return this.db.applications.create({
        data: {
          course_id: id,
          price: course.length * 500
        }
      })
    }

    throw new NotFoundException("Nem létezik ilyen kurzus");
  }
}
