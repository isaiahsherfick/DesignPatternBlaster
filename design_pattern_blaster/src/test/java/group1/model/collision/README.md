## Test Package for Collisions package

This package is a test package for `group1.model.collision` package.

The `group1.model.collision` package includes a `HitBox` class which is a rectangular hit-box around a given Sprite. The `HitBox` class is used to check for collisions. The `CollisionManager` uses the `HitBox` class to check for collisions between two HitBoxes.

#### Adding new Tests

Any change in implementation of the `HitBox` class could change the way HitBox works, which might require a change in tests. However, for the scope of this project, we do not expect a lot to change for tests in collisions.
