//import { courseResolver } from './../guards/course.resolver';
import { Injectable } from '@angular/core';
import { Course } from '../model/course';
import { HttpClient } from '@angular/common/http';
import { delay, first, tap } from 'rxjs';
import { CoursePage } from '../model/course-page';

@Injectable({
  providedIn: 'root'
})
export class CoursesService {

  private readonly API = 'api/courses';

  constructor(private httpClient: HttpClient) { }

  list(page = 0, pageSize = 10) {
    return this.httpClient.get<CoursePage>(this.API, {params: { page, pageSize }})
     .pipe(
      first()
     );
  }

  loadById(id: string){
    return this.httpClient.get<Course>(`${this.API}/${id}`);
  }

  save(record: Partial<Course>) {
    if (record._id) {
      return this.update(record);
    }
    return this.create(record);
  }

  remove(id: string){
    return this.httpClient.delete(`${this.API}/${id}`);
  }


  private create(record: Partial<Course>){
    return this.httpClient.post<Course>(this.API, record);
  }

  private update(record: Partial<Course>){
    return this.httpClient.put<Course>(`${this.API}/${record._id}`, record);
  }
}
