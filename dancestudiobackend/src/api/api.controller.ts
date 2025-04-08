import {BadRequestException, Body, Controller, Get, Param, Post} from '@nestjs/common';
import { ApiService } from './api.service';
import {CreateCourseDto} from "./dto/CreateCourse.dto";

@Controller('api')
export class ApiController {
  constructor(private readonly apiService: ApiService) {}

  @Get('courses')
  getCourses(){
    return this.apiService.getCourses()
  }

  @Post('courses')
  createCourse(@Body() createCourseDto: CreateCourseDto){
    try{
      return this.apiService.createCourse(createCourseDto);
    }
    catch (e) {
      throw new BadRequestException(e.message())
    }
  }

  @Post('courses/:id/apply')
  applyToCourse(@Param('id') id: string){
    return this.apiService.applyToCourse(+id);
  }
}
