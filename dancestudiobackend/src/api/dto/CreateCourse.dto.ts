import {IsEnum, IsNotEmpty, IsNumber, IsString} from "class-validator";

enum types{
  "solo",
  "partner",
  "group"
}

export class CreateCourseDto{
  @IsNotEmpty()
  @IsString()
  name: string

  @IsNotEmpty()
  @IsNumber()
  length: number

  @IsNotEmpty()
  @IsString()
  @IsEnum(types)
  type: string

  @IsNotEmpty()
  @IsString()
  instructor: string
}
