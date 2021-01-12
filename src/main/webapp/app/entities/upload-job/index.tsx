import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import UploadJob from './upload-job';
import UploadJobDetail from './upload-job-detail';
import UploadJobUpdate from './upload-job-update';
import UploadJobDeleteDialog from './upload-job-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={UploadJobUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={UploadJobUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={UploadJobDetail} />
      <ErrorBoundaryRoute path={match.url} component={UploadJob} />
    </Switch>
    <ErrorBoundaryRoute exact path={`${match.url}/:id/delete`} component={UploadJobDeleteDialog} />
  </>
);

export default Routes;
