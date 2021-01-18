#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""
Created on Wed Jan  6 18:49:30 2021

@author: TH
"""
class Solution:
    def numberOfMatches(self, n):
        match_c = 0
        def calc_matches(match_c, teamn):
            
            if teamn == 1:
                return match_c
            
            if teamn % 2 == 0:
                match_c += teamn // 2
                teamn = teamn // 2
                return calc_matches(match_c, teamn)
            else:
                match_c += (teamn - 1) // 2
                teamn = (teamn - 1) // 2 + 1
                return calc_matches(match_c, teamn)
        return calc_matches(match_c, n)