import { ActivatedRouteSnapshot, ResolveFn, RouterStateSnapshot } from '@angular/router';
import { Observable, of } from 'rxjs';

import { Course } from '../model/course';
import { CoursesService } from '../services/courses.service';
import { inject } from '@angular/core';

export const CourseResolver: ResolveFn<Observable<Course>> = (
  route: ActivatedRouteSnapshot,
  state: RouterStateSnapshot,
  service: CoursesService = inject(CoursesService)) => {

  if (route.params?.['id']){
    return service.loadById(route.params['id']);
  }
  return of({_id: '', name: '', category: ''});
};

