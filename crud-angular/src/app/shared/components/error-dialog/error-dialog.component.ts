import { Component, inject } from '@angular/core';
import { MAT_DIALOG_DATA } from '@angular/material/dialog';

@Component({
    selector: 'app-error-dialog',
    templateUrl: './error-dialog.component.html',
    styleUrl: './error-dialog.component.scss',
    standalone: false
})
export class ErrorDialogComponent {
  data = inject(MAT_DIALOG_DATA);

}
