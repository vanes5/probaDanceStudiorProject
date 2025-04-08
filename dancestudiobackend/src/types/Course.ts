import {Application} from "./Application";



export interface Course {
  id: number,
  name: string,
  type: string,
  length: number,
  instructor: string
  created_at: Date,
  updated_at: Date,
  applications: Application[]
}
