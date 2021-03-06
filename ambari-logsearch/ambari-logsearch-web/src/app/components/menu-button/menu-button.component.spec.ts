/**
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import {NO_ERRORS_SCHEMA} from '@angular/core';
import {async, ComponentFixture, TestBed} from '@angular/core/testing';
import {TranslationModules} from '@app/test-config.spec';
import {StoreModule} from '@ngrx/store';
import {AppSettingsService, appSettings} from '@app/services/storage/app-settings.service';
import {ComponentActionsService} from '@app/services/component-actions.service';
import {FilteringService} from '@app/services/filtering.service';

import {MenuButtonComponent} from './menu-button.component';

describe('MenuButtonComponent', () => {
  let component: MenuButtonComponent;
  let fixture: ComponentFixture<MenuButtonComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [MenuButtonComponent],
      imports: [
        StoreModule.provideStore({
          appSettings
        }),
        ...TranslationModules
      ],
      providers: [
        AppSettingsService,
        ComponentActionsService,
        FilteringService
      ],
      schemas: [NO_ERRORS_SCHEMA]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(MenuButtonComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create component', () => {
    expect(component).toBeTruthy();
  });

  describe('#hasSubItems', () => {
    const cases = [
      {
        subItems: null,
        hasSubItems: false,
        title: 'no sub-items'
      },
      {
        subItems: [],
        hasSubItems: false,
        title: 'empty sub-items array'
      },
      {
        subItems: [{}],
        hasSubItems: true,
        title: 'sub-items present'
      }
    ];

    cases.forEach((test) => {
      it(test.title, () => {
        component.subItems = test.subItems;
        expect(component.hasSubItems).toEqual(test.hasSubItems);
      });
    });
  });

  describe('#hasCaret', () => {
    const cases = [
      {
        subItems: null,
        hideCaret: false,
        hasCaret: false,
        title: 'no sub-items'
      },
      {
        subItems: [],
        hideCaret: false,
        hasCaret: false,
        title: 'empty sub-items array'
      },
      {
        subItems: [{}],
        hideCaret: false,
        hasCaret: true,
        title: 'sub-items present, caret not hidden'
      },
      {
        subItems: [{}],
        hideCaret: true,
        hasCaret: true,
        title: 'sub-items present, caret hidden'
      }
    ];

    cases.forEach((test) => {
      it(test.title, () => {
        component.subItems = test.subItems;
        component.hideCaret = test.hideCaret;
        expect(component.hasSubItems).toEqual(test.hasCaret);
      });
    });
  });
});
