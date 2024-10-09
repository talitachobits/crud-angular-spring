import { Location } from '@angular/common';
import { Component } from '@angular/core';
import { FormGroup, NonNullableFormBuilder, Validators } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { CoursesService } from '../../services/courses.service';
import { ActivatedRoute } from '@angular/router';
import { Course } from '../../model/course';

@Component({
  selector: 'app-course-form',
  templateUrl: './course-form.component.html',
  styleUrl: './course-form.component.scss'
})
export class CourseFormComponent {

  form: FormGroup;

  constructor(private formBuilder: NonNullableFormBuilder,
    private courseService: CoursesService,
    private snackBar: MatSnackBar,
    private location: Location,
    private route: ActivatedRoute) {
      const course: Course = this.route.snapshot.data['course'];
      this.form = this.formBuilder.group({
        _id: [''],
        name: ['', [Validators.required, Validators.minLength(5), Validators.maxLength(100)]],
        category: ['', [Validators.required]]
      })
      this.form.setValue({
        _id: course._id,
        name: course.name,
        category: course.category
      });
  }


  onSubmit(){
    this.courseService.save(this.form.value).subscribe({
      next: (result) => this.onSucess(),
      error: () => {
        this.onError();
      },
    });
  }

  onCancel(){
    this.location.back();
  }

  errorMessage(fieldName: string){
    const field = this.form.get(fieldName);

    if (field?.hasError('required')) {
      return 'Campo obrigatório';
    }

    if (field?.hasError('minlength')) {
      const requiredLength = field.errors ? field.errors['minlength']['requiredLength'] : 5;
      return `Tamanho mínimo precisa ser de ${requiredLength} caracteres`;
    }

    if (field?.hasError('maxlength')) {
      const requiredLength = field.errors ? field.errors['maxlength']['requiredLength'] : 100;
      return `Tamanho máximo excedido de ${requiredLength} caracteres`;
    }
    return 'Campo Inválido';

  }

  private onSucess(){
    this.snackBar.open('Curso salvo com sucesso!', '', {duration: 5000});
    this.onCancel();
  }

  private onError(){
    this.snackBar.open('Erro ao salvar curso.', '', {duration: 5000});
  }

}
