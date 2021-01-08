import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import DefaultTemplatesFields from './default-templates-fields';
import DefaultTemplatesFieldsDetail from './default-templates-fields-detail';
import DefaultTemplatesFieldsUpdate from './default-templates-fields-update';
import DefaultTemplatesFieldsDeleteDialog from './default-templates-fields-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={DefaultTemplatesFieldsUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={DefaultTemplatesFieldsUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={DefaultTemplatesFieldsDetail} />
      <ErrorBoundaryRoute path={match.url} component={DefaultTemplatesFields} />
    </Switch>
    <ErrorBoundaryRoute exact path={`${match.url}/:id/delete`} component={DefaultTemplatesFieldsDeleteDialog} />
  </>
);

export default Routes;
