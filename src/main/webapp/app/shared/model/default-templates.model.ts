import { Moment } from 'moment';
import { IDefaultTemplatesFields } from 'app/shared/model/default-templates-fields.model';

export interface IDefaultTemplates {
  id?: number;
  name?: string;
  contentStyle?: string;
  comments?: string;
  approvedTemplateId?: string;
  createdBy?: string;
  createdOn?: string;
  updatedBy?: string;
  updatedOn?: string;
  defaultTemplatesFields?: IDefaultTemplatesFields[];
}

export const defaultValue: Readonly<IDefaultTemplates> = {};
