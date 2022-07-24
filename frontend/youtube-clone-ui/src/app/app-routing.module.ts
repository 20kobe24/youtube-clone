import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule, Routes } from '@angular/router';
import { UploadVideoComponent } from './upload-video/upload-video.component';

const routes: Routes = [
  {
    path: 'upload-video',
    component: UploadVideoComponent,
  },
];

@NgModule({
  declarations: [],
  imports: [RouterModule.forRoot(routes)],
})
export class AppRoutingModule {}
