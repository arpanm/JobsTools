import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import QCashUploadRow from './q-cash-upload-row';
import QCashUploadRowDetail from './q-cash-upload-row-detail';
import QCashUploadRowUpdate from './q-cash-upload-row-update';
import QCashUploadRowDeleteDialog from './q-cash-upload-row-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={QCashUploadRowUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={QCashUploadRowUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={QCashUploadRowDetail} />
      <ErrorBoundaryRoute path={match.url} component={QCashUploadRow} />
    </Switch>
    <ErrorBoundaryRoute exact path={`${match.url}/:id/delete`} component={QCashUploadRowDeleteDialog} />
  </>
);

export default Routes;
