import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import DefaultTemplates from './default-templates';
import DefaultTemplatesDetail from './default-templates-detail';
import DefaultTemplatesUpdate from './default-templates-update';
import DefaultTemplatesDeleteDialog from './default-templates-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={DefaultTemplatesUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={DefaultTemplatesUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={DefaultTemplatesDetail} />
      <ErrorBoundaryRoute path={match.url} component={DefaultTemplates} />
    </Switch>
    <ErrorBoundaryRoute exact path={`${match.url}/:id/delete`} component={DefaultTemplatesDeleteDialog} />
  </>
);

export default Routes;
