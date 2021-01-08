import React from 'react';
import { Switch } from 'react-router-dom';

// eslint-disable-next-line @typescript-eslint/no-unused-vars
import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import DefaultTemplates from './default-templates';
import DefaultTemplatesFields from './default-templates-fields';
/* jhipster-needle-add-route-import - JHipster will add routes here */

const Routes = ({ match }) => (
  <div>
    <Switch>
      {/* prettier-ignore */}
      <ErrorBoundaryRoute path={`${match.url}default-templates`} component={DefaultTemplates} />
      <ErrorBoundaryRoute path={`${match.url}default-templates-fields`} component={DefaultTemplatesFields} />
      {/* jhipster-needle-add-route-path - JHipster will add routes here */}
    </Switch>
  </div>
);

export default Routes;
